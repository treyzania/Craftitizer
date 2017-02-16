use std::env;
use std::string::String;
use std::fs;
use std::fs::File;
use std::path::{Path, PathBuf};
use std::io::{BufRead, BufReader};
use std::iter::Iterator;
use std::process::Command;

extern crate libc;

const ALLOWED_DIRS_FILENAME: &'static str = "/etc/craftitizer/crsplit-allowed";

fn main() {

	if unsafe { libc::getuid() } != 0 {
		println!("error: must be run as root");
		return;
	}

	let args: Vec<String> = env::args().collect();

	if args.len() != 3 {
		println!("usage: {} [--combine|--uncombine] <path>", args[0]);
		return;
	}

	let dirpath = Path::new(&args[2]);
	if !check_path_ok(&dirpath) {

		println!("error: requested path not allowed");
		return;

	}

	let action_str = &args[1];
	if action_str == "--combine" || action_str == "-c" {
		combine(&dirpath);
	} else if action_str == "--uncombine" || action_str == "-u" {
		uncombine(&dirpath);
	} else {
		println!("error: invalid action argument: {}", action_str);
	}

}

fn check_path_ok(dir: &Path) -> bool {

	let ad_path = Path::new(ALLOWED_DIRS_FILENAME);
	let ad = match File::open(&ad_path) {
		Err(_) => panic!("Couldn't find file."),
		Ok(f) => f
	};

	let reader = BufReader::new(ad);

	let mut ok = false;
	for line in reader.lines() {
		ok |= dir.starts_with(Path::new(&line.unwrap()));
	}

	ok

}

fn combine(dir: &Path) {

	let mut exec = PathBuf::from(dir.to_path_buf());
	exec.push("exec");
	make_maybe(&exec);

	let mut data = PathBuf::from(dir.to_path_buf());
	data.push("data");
	make_maybe(&data);

	let mut work = PathBuf::from(dir.to_path_buf());
	work.push(".work");
	make_maybe(&work);

	let mut live = PathBuf::from(dir.to_path_buf());
	live.push("live");
	make_maybe(&live);

	let opts = format!("lowerdir={},upperdir={},workdir={},nosuid,nodev",
		exec.to_str().unwrap(),
		data.to_str().unwrap(),
		work.to_str().unwrap());

	let live_path_str = live.to_str().unwrap();

	// TODO Make this report problems.
	Command::new("mount")
		.arg("-t")
		.arg("overlay")
		.arg("overlayfs")
		.arg("-o")
		.arg(opts)
		.arg(live_path_str)
		.output()
		.expect("failed to execute mount process");

}

fn make_maybe(dir: &Path) {

	if !dir.exists() {
		if fs::create_dir_all(dir).is_err() {
			println!("bitch what?");
		}
	}

}

fn uncombine(dir: &Path) {

	let mut live = PathBuf::from(dir.to_path_buf());
	live.push("live");

	// TODO Make this report problems.
	Command::new("umount")
		.arg(live.to_str().unwrap())
		.output()
		.expect("failed to execute umount process");

}

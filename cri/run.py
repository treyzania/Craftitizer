#!/usr/bin/env python3

import os
import os.path as paths
import argparse

import crilib.server

parser = argparse.ArgumentParser(description="Run the server.")
parser.add_argument("--path",
	dest="server_dir", help="The dir the server.yml file is in.")
parser.add_argument("--verbose", "-v",
	dest="verbose", action="store_const", const=True, default=False,
	help="If we should use verbose output")
args = vars(parser.parse_args())
verbose = args["verbose"]

sdir = paths.join(os.getcwd())
if 'server_dir' in args:
	sdir = args['server_dir']

if not paths.isdir(sdir):
	raise Exception("Path is not a directory.")

while not paths.exists(paths.join(sdir, "server.yml")):
	parent = paths.dirname(sdir)
	if verbose:
		print("Looking at:", parent)
	if paths.samefile(parent, '/'):
		raise Exception("Couldn't find server directory.")
	sdir = parent

cfg_name = paths.join(paths.abspath(sdir), "server.yml")

if verbose:
	print("Using config:", cfg_name)
s = crilib.server.Server(paths.abspath(cfg_name))
s.executor.start()

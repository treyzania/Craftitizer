import os.path
import urllib.request
import importlib.util as imu

def get_url_suffixes(desc):
	urls = []
	urls.append(desc.name + "/" + desc.version + "/" + desc.name + "-" + desc.version + ".py")
	urls.append(desc.name + "/" + desc.version + ".py")
	urls.append(desc.name + "/" + desc.version + "/pkgconf.py")
	urls.append(desc.name + "-" + desc.version + ".py")
	return urls

class RepoMeta:
	def __init__(self, name, url):
		self.name = name
		self.url = url

	def get_package_def_string(self, desc):
		suffixes = get_url_suffixes(desc)
		for suffix in suffixes:
			try:
				req = urllib.request.urlopen(self.url + suffix)
				return req.read()
			except:
				continue
		return None

class PackageModuleDefinition:
	def __init__(self, modname, version):
		self.modname = modname
		self.version = version

class PackageMeta:
	def __init__(self, name, version):
		self.name = name
		self.version = version

cache_dir = os.path.expanduser("~/.cache/craftitizer/repo")
repos = [] # TODO Set up repos.

def __gen_cached_package_path(pkg):
	return os.join(cache_dir, pkg.name, pkg.version, "pkgconf.py")

def __descriptor(source):
	line = source.split("\n", 1)[0]
	parts = line.split(" ")
	if not parts[0] == "#cripackage" or len(parts) != 3:
		raise Exception("Package isn't a #cripackage!")
	name = parts[1]
	ver = parts[2]
	return PackageModuleDefinition(name, ver)

def __descriptor_from_file(path):
	with os.open(path, "r") as f:
		return __descriptor(f.read())

def find_package_def(pkg):
	cache_path = __gen_cached_package_path(pkg)
	if not os.path.exists(cache_path):
		for r in repos:
			data = r.get_package_def_string(pkg)
			if data != None:
				try:
					desc = __descriptor(data)
				except Exception:
					raise Exception("Package " + pkg.name + " " + pkg.version + " is invalid at " + r.name + ".")
				with os.open(cache_path, "w") as tocache:
					tocache.write(data)
				return cache_path

		raise Exception("Could not find package " + pkg.name + " " + pkg.version + "!")
	else:
		return cache_path

def __load_module(path, modname):
	spec = imu.spec_from_file_location(modname, path)
	confmod = imu.module_from_spec(spec)
	spec.loader.exec_module(confmod)
	return confmod

def __create_package_module_from_file(pkg):
	path = find_package_def(pkg)
	desc = __descriptor_from_file(path)
	return __load_module("cripkg." + desc.name, path)

class PackageBundle:
	def __init__(self, meta, desc, mod):
		self.metadata = meta
		self.description = desc
		self.module = mod
	def get_cache_path(self):
		return __gen_cached_package_path(self.metadata)

def init_package(pkg):
	path = find_package_def(pkg)
	desc = __descriptor_from_file(path)
	mod = __create_package_module_from_file(path)
	return PackageBundle(pkg, desc, mod)

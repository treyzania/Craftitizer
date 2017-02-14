import sys
import importlib.util as imu

def construct_config(name, path):
	spec = imu.spec_from_file_location(name, path)
	confmod = imu.module_from_spec(spec)
	spec.loader.exec_module(confmod)
	
	return confmod

class PackageLoader:
	
	def __init__(self):
		self.loaded = []
	
	def load_file_package(name, path):
		pass

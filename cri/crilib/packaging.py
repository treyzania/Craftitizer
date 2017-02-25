import os.path

import crilib.repositories as repos
import crilib.resources

# Stages:
# * load
# * install
# * configure
# * postconf
# * start
# * poststart
# * crash
# * exists
# * preupgrade
# * postupgrade
# * prestop

class Context:
	def __init__(self):
		pass

class LoadContext(Context):
	deps = []
	def __init__(self, ldr):
		self.loader = ldr
	def depend(name, version):
		self.deps.append(PackageMeta(name, version))

class InstallationRequest:
	def __init__(self, res, path, serv):
		self.resource = res
		self.path = path
		self.server = serv
	def install(self, server):
		res.install(os.paths.join(self.server.data_dir, self.path))

class InstallContext(Context):
	requests = []
	def __init__(self, pkg, server):
		self.package = pkg
		self.server = server
	def download(self, url, dest):
		res = crilib.resource.request_simple_url("pkg." + self.package.name, url)
		self.requests.append(InstallationRequest(res, dest, self.server))

class PackageLoader:
	package_bundles = []
	use_counts = {}

	def find_inited_package(self, name):
		for p in self.package_bundles:
			if p.metadata.name == name:
				return p
		return None

	def init_pkg(self, pkg):
		bundle = self.find_inited_package(pkg.name)
		if bundle == None:
			try:
				bundle = repos.init_package(pkg)
			except repos.PackageException:
				raise Exception("Couldn't resolve dependencies.")

			self.package_bundles.append(bundle)
			self.use_counts[pkg.name] = 1
			self.__load_pkg(bundle)
		else:
			self.use_counts[pkg.name] += 1

	def __load_pkg(self, bundle):
		ctx = LoadContext(self)
		if 'load' in dir(bundle.module):
			try:
				bundle.module.load(ctx)
			except:
				print("error: exception when load()-ing package:", bundle.metadata.name)
		for dep in ctx.deps:
			self.init_pkg(dep)

	def get_call_order(self):
		return list(reversed(sorted(self.use_counts, key=self.use_counts.get)))

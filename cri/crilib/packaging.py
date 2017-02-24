import crilib.repositories as repos

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
	def __init__(self, name):
		self.pkgname = name

class LoadContext(Context):
	def __init__(self, ldr):
		super(name)
		self.loader = ldr
		self.deps = []
	def depend(name, version):
		self.deps.append(PackageMeta(name, version))

class PackageLoader:

	def __init__(self):
		self.package_bundles = []
		self.use_counts = {}

	def __find_loaded_package(self, name):
		for p in self.package_bundles:
			if p.metadata.name == name:
				return p
		return None

	def init_pkg(self, pkg):
		bundle = self.__find_loaded_package(pkg.name)
		if bundle == None:
			bundle = repos.init_package(pkg)
			self.package_bundles.append(bundle)
			self.use_counts[pkg.name] = 1
			self.__load_pkg(bundle)
		else:
			self.use_counts[pkg.name] += 1

	def __load_pkg(self, bundle):
		ctx = LoadContext(self)
		if 'load' in dir(bundle.module):
			bundle.module.load(ctx)
		for dep in ctx.deps:
			self.init_pkg(dep)

	def get_call_order(self):
		return list(reversed(sorted(self.use_counts, key=self.use_counts.get)))

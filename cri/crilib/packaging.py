import crilib.repositories

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
	def __init__(self, name, ldr):
		super(name)
		self.loader = ldr
		self.deps = []
	def depend(name, version):
		self.deps.append(PackageMeta(name, version))

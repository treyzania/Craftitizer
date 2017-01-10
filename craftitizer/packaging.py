
class Package:
	
	def stage_load(self, ctx):
		print(ctx.pkgname)
		pass
	
	def stage_install(self, ctx):
		pass
	
	def stage_configure(self, ctx):
		pass
	
	def stage_postconf(self, ctx):
		pass
	
	def stage_start(self, ctx):
		pass
	
	def stage_poststart(self, ctx):
		pass
	
	def stage_crash(self, ctx):
		pass
	
	def stage_exit(self, ctx):
		pass
	
	def stage_preupgrade(self, ctx):
		pass
	
	def stage_postupgrade(self, ctx):
		pass
	
	def state_prestop(self, ctx):
		pass

class PackageMeta:
	def __init__(self, name, version):
		self.name = name
		self.version = version

class Context:
		
	def __init__(self, name):
		self.pkgname = name

class LoadContext(Context):
	
	def __init__(self, name, ldr):
		super(name)
		self.loader = ldr
		self.deps = []
		self.repos = []
	
	def depend(name, version):
		self.deps.append(PackageMeta(name, version))
	
	def specify_repo(name, url):
		self.repos.append({'name': name, 'url': url})



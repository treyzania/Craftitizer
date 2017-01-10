
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

class Context:
		
	def __init__(self, name):
		self.pkgname = name
	

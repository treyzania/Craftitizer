import urlllib.request

def get_url_suffixes(desc):
	urls = []
	urls.append(desc.name + '/' + desc.version + '/' + desc.name + '-' + desc.version + '.py')
	urls.append(desc.name + '/' + desc.version + '/pkgconf.py')
	urls.append(desc.name + '-' + desc.version + '.py')
	return urls

class RepoMeta:
	def __init__(self, name, url):
		self.name = name
		self.url = url
	
	def get_package_def_string(desc):
		suffixes = get_url_suffixes(desc)
		for suffix in suffixes:
			try:
				req = urllib.request.urlopen(self.url + suffix)
				return req.read()
			except:
				continue

import os
import os.path as paths
import urllib.request
import hashlib
import shutil

import crilib.repositories as repos

class Resource:
    def __init__(self, namespace, cid, url, artifact):
        self.namespace = namespace
        self.cache_id = cid;
        self.url = url
        self.artifact = artifact

    def get_cache_path():
        suffix = paths.join(self.namespace, self.cache_id, self.artifact)
        return paths.join(repos.cache_dir, "res", suffix)

    def is_cached():
        return paths.exists(self.get_cache_path())

    def refresh_cache():
        path = self.get_cache_path()
        if self.is_cached():
            os.remove(path)
        if not paths.exists(paths.dirname(path)):
            os.makedirs(path)
        res = urllib.request.urlopen(self.url)
        with open(path, mode="w") as cache:
            cache.write(res.read())

    def install(path):
        if not self.is_cached():
            self.refresh_cache()
        if path != None: # If they pass None we at least make sure it's cached.
            shutil.copyfile(self.get_cache_path, path(), follow_symlinks=True)

def request_simple_url(namespace, url):
    url_hash = hashlib.sha256(url.encode("UTF-8")).hexdigest()
    return Resource(namespace, "", url, url_hash + ".sha256")

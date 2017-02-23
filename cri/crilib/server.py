import os
import ruamel.yaml
import subprocess

class Server(object):
    def __init__(self, path):
        content = ""
        with open(path, 'r') as data:
            content = data.read()
        cfg = ruamel.yaml.load(content, ruamel.yaml.RoundTripLoader)
        self.path = path
        self.name = cfg["name"]
        self.executor = cfg["exec"]["name"]
        self.execparams = cfg["exec"]["params"]
        self.envvars = cfg["envvars"]
        self.group = cfg["group"]

class Executor(object):
    def __init__(self, serv):
        self.server = serv
    def start(self):
        print("error: invalid server executor")

class SimpleJavaExecutor(Executor):
    def __init__(self, serv):
        super(self)
    def start(self):
        print("starting server ", self.server.name);

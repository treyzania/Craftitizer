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
        exe_cfg = cfg["exec"]
        self.executor = make_executor(self, exe_cfg["name"], exe_cfg["params"])
        self.envvars = cfg["envvars"]
        self.group = cfg["group"]

class Executor(object):
    def __init__(self, serv):
        self.server = serv
    def start(self):
        print("error: invalid server executor")

class DebugExecutor(Executor):
    def __init__(self, serv, params):
        super().__init__(serv)
    def start(self):
        print("Started debug executor, for server:", self.server.name)

class SimpleJavaExecutor(Executor):
    def __init__(self, serv, params):
        super().__init__(serv)
    def start(self):
        pass

executors = {
    'test': DebugExecutor,
    'simplejava': SimpleJavaExecutor
}

def make_executor(serv, name, params):
    global executors
    clazz = executors[name];
    return clazz(serv, params);

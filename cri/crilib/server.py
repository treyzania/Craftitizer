import os
import os.path
import subprocess
import ruamel.yaml

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

        self.jxms = params["start_mem"]
        self.jxmx = params["max_mem"]
        self.jar = params["jar"]
        self.extras = [] # TODO Implement this.

        if "java_cmd" in params.values():
            self.java = params["java_cmd"]
        else:
            self.java = "java"

        if "subdir" in params.values():
            self.subdir = params["subdir"]
        else:
            self.subdir = "server"

    def start(self):
        try:
            os.stat(self.subdir)
        except IOError:
            os.mkdir(self.subdir)

        if not os.path.isfile(os.path.join(self.subdir, self.jar)):
            raise Exception("Server jar does not exist.")

        abspath = os.path.abspath(self.subdir)
        command = []
        command.append("-Xms" + self.jxms + "M")
        command.append("-Xmx" + self.jxmx + "M")
        command.append("-jar")
        command.append(self.jar)
        command.append("nogui")

         # TODO Take env=[...] parameters.
        with subprocess.Popen(command, executable=self.java, cwd=abspath) as proc:
            ret = proc.wait()
            if ret != 0:
                print("Server exited with non-zero return code " + str(ret))

executors = {
    'test': DebugExecutor,
    'simplejava': SimpleJavaExecutor
}

def make_executor(serv, name, params):
    global executors
    clazz = executors[name];
    return clazz(serv, params);

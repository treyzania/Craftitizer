import os
import os.path as paths
import subprocess
import ruamel.yaml

class Server:
    def __init__(self, path):
        content = ""
        with open(path, 'r') as data:
            content = data.read()
        cfg = ruamel.yaml.load(content, ruamel.yaml.RoundTripLoader)
        self.server_dir = cfg['path'] if 'path' in cfg.values() else paths.dirname(path)
        self.name = cfg["name"]
        exe_cfg = cfg["exec"]
        self.executor = make_executor(self, exe_cfg["name"], exe_cfg["params"])
        self.envvars = cfg["envvars"]
        self.group = cfg["group"]

    def get_exec_dir(self):
        return self.server_dir
    def get_runtime_dir(self):
        return ""

class Executor:
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

        if not paths.isfile(paths.join(serv.server_dir, self.subdir, self.jar)):
            raise Exception("Server jar does not exist.")

        abspath = paths.abspath(self.subdir)
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

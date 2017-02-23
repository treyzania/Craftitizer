#!/usr/bin/env python3

import crilib.server

s = crilib.server.Server("server_template.yml")
s.executor.start()

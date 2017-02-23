#!/usr/bin/env python3

import crilib.server

s = crilib.server.Server("server_template.yml")
print(s.name)

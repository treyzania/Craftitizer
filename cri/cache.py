#!/usr/bin/env python3

import argparse

import crilib.repositories as repos

parser = argparse.ArgumentParser(description="Pull a package into the cache.")
parser.add_argument("name", help="The package name.")
parser.add_argument("ver", help="The package version.")
args = parser.parse_args()

path = repos.find_package_def(repos.PackageMeta(args.name, args.ver))
print("Cached to:", path)

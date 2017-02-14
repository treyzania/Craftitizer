#!/bin/bash

# Config
INSTALL_DIR='.'

# Figure out where we are.
EXEC=$INSTALL_DIR/$1.*

# Actually execute the program.
$EXEC ${@:2}

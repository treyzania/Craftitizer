#!/bin/sh

INSTALL_PATH='/usr/local/bin/crsplit'

cargo build --release

sudo cp target/release/crsplit $INSTALL_PATH
sudo chmod u+s $INSTALL_PATH
sudo touch /etc/craftitizer/crsplit-allowed

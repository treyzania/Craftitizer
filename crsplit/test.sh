#!/bin/sh

MOUNT_DIR=/mnt/test

mkdir -p $MOUNT_DIR
echo "Building..."
cargo build
echo "Done!"

echo "You shouldn't see anything here:"
mount | grep $MOUNT_DIR
echo "\nMounting..."
sudo ./target/debug/crsplit -c $MOUNT_DIR
echo "You should see one thing here:"
mount | grep $MOUNT_DIR
echo "\nUnmounting..."
sudo ./target/debug/crsplit -u $MOUNT_DIR
echo "You should see the same number of things as the first time here, probably nothing:"
mount | grep $MOUNT_DIR
echo "\nDone!"

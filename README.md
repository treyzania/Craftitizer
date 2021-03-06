#Craftitizer

## cri

`cri`, short for `CRaftitizer Installation`, is for setting up and running your
Minecraft server instance.  It is written in Python and uses a package system
based in Python modules dynamically loaded at run-time to provide rich control
over the server installation.

*// TODO Add more here*

## crsplit

`crsplit` is another command-line utility for setting up a directory structure
on Linux that allows for cri's crsplit server executor to run like things. It
uses overlayfs with the following directories:

* **exec** : Where runtime and configuration data is stored.

* **data** : Where world data stored at runtime is stored.

* **.work** : The internal overlayfs "work" directory.

* **live** : Mount point for the composited data where the server is run.

It does not persist across reboots, but cri has a mechanism for detecting if
crsplit needs to be run again.  The executable should be owned by root and be
marked as setuid otherwise everything doesn't work.  It mounts the "filesystem"
with nosetuid so there is not security vulnerability.

## kkgenconf

`kkgenconf` essentially just loads a Bukkit plugin and invokes
`JavaPlugin:onEnable()` on them.  But in order to do that it has to pretend to
set up a whole server, so this implements a small portion of the Bukkit API. And
at that point it was also easy enough to add a "game loop" that you can
essentially use as just something to keep simple stuff like VoteSend running
without taking up a port or worrying about ticking worlds or handling players or
anything.


import crilib.repositories
import crilib.packaging
import crilib.server

serv = crilib.server.Server("server.yml")
ldr = crilib.packaging.PackageLoader()

pkg = crilib.repositories.PackageMeta("minecraft-vanilla", "MC1.11.2")
ldr.init_pkg(pkg)

ictx = crilib.packaging.InstallContext(pkg, serv)
bundle = ldr.find_inited_package("minecraft-vanilla")
bundle.module.install(ictx)

for p in ictx.requests:
    p.install()

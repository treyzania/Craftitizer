package com.treyzania.craftitizer.plugin.bukkit;

import java.io.File;

import com.treyzania.craftitizer.Installation;
import com.treyzania.craftitizer.plugin.AbstractPlugin;

public class BukkitPlugin extends AbstractPlugin {

	public BukkitPlugin(String name) {
		super(name);
	}
	
	@Override
	public File getDataDirectory(Installation install) {
		return install.getBaseDirectory();
	}
	
	@Override
	public File getConfigFile(Installation install) {
		return new File(this.getDataDirectory(install), this.getName());
	}
	
}

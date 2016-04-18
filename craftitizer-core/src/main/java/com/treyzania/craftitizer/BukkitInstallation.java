package com.treyzania.craftitizer;

import java.io.File;

import com.treyzania.craftitizer.plugin.bukkit.BukkitPlugin;

/**
 * Used to represent server software like CreaftBukkit and/or Spigot.
 * 
 * @author Treyzania
 *
 */
public class BukkitInstallation extends MinecraftInstallation implements PluggableInstallation<BukkitPlugin> {

	public BukkitInstallation(File base) {
		super(base);
	}
	
	@Override
	public File getPluginsDirectory() {
		return new File(this.getBaseDirectory(), "plugins");
	}

	@Override
	public String getPluginExtension() {
		return "jar";
	}
	
}

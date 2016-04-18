package com.treyzania.craftitizer;

import java.io.File;

/**
 * Used to represent server software like CreaftBukkit and/or Spigot.
 * 
 * @author Treyzania
 *
 */
public class RichInstallation extends MinecraftInstallation {

	public RichInstallation(File base) {
		super(base);
	}
	
	/**
	 * @return A File object representing the plugins data directory.
	 */
	public File getPluginsDirectory() {
		return new File(this.getBaseDirectory(), "plugins");
	}
	
}

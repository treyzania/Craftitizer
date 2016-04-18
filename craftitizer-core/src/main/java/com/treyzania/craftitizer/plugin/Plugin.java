package com.treyzania.craftitizer.plugin;

import java.io.File;

import com.treyzania.craftitizer.Installation;

public interface Plugin {

	/**
	 * @return The plugin's simple name, without any version numbers or extra data.
	 */
	public String getName();
	
	/**
	 * @return A File object representing the data folder of the plugin, null if not applicable.
	 */
	public File getDataDirectory(Installation install);
	
	/**
	 * @return A File object representing the main config file for the plugin, null if not applicable. 
	 */
	public File getConfigFile(Installation install);
	
}

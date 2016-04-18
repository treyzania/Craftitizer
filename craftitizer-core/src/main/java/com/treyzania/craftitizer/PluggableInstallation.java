package com.treyzania.craftitizer;

import java.io.File;

import com.treyzania.craftitizer.plugin.Plugin;

public interface PluggableInstallation<T extends Plugin> extends Installation {

	/**
	 * @return A File object representing the plugins data directory.
	 */
	public File getPluginsDirectory();
	
	/**
	 * @return A String of the characters that appear after the dot in a file name to represent the extension.
	 */
	public String getPluginExtension();
	
}

package com.treyzania.craftitizer.plugin;

import java.io.File;

import com.treyzania.craftitizer.PluggableInstallation;

public class PluginInstance {

	public static final String JAR_EXTENSION = ".jar";
	public static final String PLUGIN_DATA_EXTENSION = ".plugin.conf";
	
	public final PluggableInstallation<?> installation;
	public final Plugin plugin;
	public final String pluginVersion;
	
	public PluginInstance(PluggableInstallation<?> install, Plugin plug, String ver) {
		
		this.installation = install;
		
		this.plugin = plug;
		this.pluginVersion = ver;
		
	}
	
	public PluginInstance(PluggableInstallation<?> install, Plugin plug) {
		this(install, plug, null);
	}
	
	/**
	 * @return A File object representing the jarfile of the plugin.
	 */
	public File getJar() {
		
		StringBuilder sb = new StringBuilder(this.plugin.getName());
		
		if (this.pluginVersion != null) {
			
			sb.append("-");
			sb.append(this.pluginVersion);
			
		}
		
		sb.append(".");
		sb.append(this.installation.getPluginExtension());
		
		return new File(this.installation.getPluginsDirectory(), sb.toString());
		
	}
	
	/**
	 * @return A File object representing the properties file used to keep track of the installed version.
	 */
	public File getPropFile() {
		return new File(this.installation.getPluginsDirectory(), this.plugin.getName() + PLUGIN_DATA_EXTENSION);
	}
	
}

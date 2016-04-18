package com.treyzania.craftitizer.plugin;

import java.io.File;

import com.treyzania.craftitizer.Installation;

public class Plugin {

	public static final String JAR_EXTENSION = ".jar";
	public static final String PLUGIN_DATA_EXTENSION = ".plugin.conf";
	
	public final Installation installation;
	public final String pluginName;
	public final String pluginVersion;
	
	public Plugin(Installation install, String name, String ver) {
		
		this.installation = install;
		
		this.pluginName = name;
		this.pluginVersion = ver;
		
	}
	
	public Plugin(Installation install, String name) {
		this(install, name, null);
	}
	
	/**
	 * @return A File object representing the jarfile of the plugin.
	 */
	public File getJar() {
		
		StringBuilder sb = new StringBuilder(this.pluginName);
		
		if (this.pluginVersion != null) {
			
			sb.append("-");
			sb.append(this.pluginVersion);
			
		}
		
		sb.append(JAR_EXTENSION);
		
		return new File(this.installation.getPluginsDirectory(), sb.toString());
		
	}
	
	/**
	 * @return A File object representing the properties file used to keep track of the installed version.
	 */
	public File getPropFile() {
		return new File(this.installation.getPluginsDirectory(), this.pluginName + PLUGIN_DATA_EXTENSION);
	}
	
}

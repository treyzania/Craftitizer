package com.treyzania.craftitizer;

import java.io.File;

public class Installation {

	private final File baseDirectory;
	
	public Installation(File base) {
		
		this.baseDirectory = base;
		
	}
	
	public File getBaseDirectory() {
		return this.baseDirectory;
	}
	
	public File getPluginsDirectory() {
		return new File(this.getBaseDirectory(), "plugins");
	}
	
}

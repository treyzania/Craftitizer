package com.treyzania.craftitizer;

import java.io.File;

public class MinecraftInstallation implements Installation {

	private final File baseDirectory;

	public MinecraftInstallation(File base) {
		
		this.baseDirectory = base;
		
	}
	
	@Override
	public File getBaseDirectory() {
		return this.baseDirectory;
	}

}

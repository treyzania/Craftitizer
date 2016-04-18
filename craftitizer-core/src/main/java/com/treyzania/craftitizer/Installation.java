package com.treyzania.craftitizer;

import java.io.File;

public interface Installation {

	/**
	 * @return A File object representing the directory where all of the server's files will be placed into.
	 */
	public File getBaseDirectory();
	
}

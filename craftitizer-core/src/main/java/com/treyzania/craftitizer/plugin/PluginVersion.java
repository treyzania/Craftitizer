package com.treyzania.craftitizer.plugin;

import java.io.File;

public class PluginVersion {
	
	private final String author;
	private final String name;
	private final String version;
	
	public PluginVersion(String author, String name, String ver) {
		
		this.author = author;
		this.name = name;
		this.version = ver;
		
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	/**
	 * @return The version up to and not including the first hyphen.
	 */
	public String getShortVersion() {
		return this.getVersion().split("-")[0];
	}
	
	public File getRelativeFile(File repoRoot) {
		return new File(new File(repoRoot, this.getAuthor()), String.format("%s-%s.jar", this.getName(), this.getVersion()));
	}
	
	/**
	 * Compares the version of this plugin to the other, and returns a relevant
	 * value.  Only works if using a versioning system like Logical Version
	 * Numbers.  And also only takes the first part before any hyphens such
	 * that Maven "SNAPSHOT" versions make sense and plugins with versions that
	 * include the Minecraft version can make a little more sense.  Ignores
	 * differing authors as authorship can change over time.
	 * 
	 * @param other The plugin to compare versions with
	 * @return 1 if this is newer, -1 if the other is newer, or 0 if equal
	 */
	public int compareVersion(PluginVersion other) {
		
		if (!this.getName().equals(other.getName())) throw new IllegalArgumentException("This plugin is not the same as the other plugin! (" + this.getName() + " vs " + other.getName() + ")");
		
		String[] myVerParts = this.getShortVersion().split("\\.");
		String[] otherVerParts = other.getShortVersion().split("\\."); 
		
		int iters = Math.min(myVerParts.length, otherVerParts.length);
		
		for (int i = 0; i < iters; i++) {
			
			String myPart = myVerParts[i];
			String otherPart = otherVerParts[i];
			
			try {
				
				int myVerInt = Integer.parseInt(myPart);
				int otherVerInt = Integer.parseInt(otherPart);
				
				int compare = Integer.compare(myVerInt, otherVerInt);
				
				if (compare < 0) {
					return 1;
				} else if (compare > 0) {
					return -1;
				} else {
					
					if (i < iters - 1) continue;
					
					if (iters > myVerParts.length) {
						return 1;
					} else if (iters > otherVerParts.length) {
						return -1;
					} else {
						return 0;
					}
					
				}
				
			} catch (NumberFormatException nfe) {
				throw new IllegalArgumentException("Version is not pure numbers before first hyphen!", nfe);
			}
			
		}
		
		return 0;
		
	}

	@Override
	public String toString() {
		return String.format("%s:%s:%s", this.getAuthor(), this.getName(), this.getVersion());
	}
	
}

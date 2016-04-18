package com.treyzania.craftitizer.plugin;

public abstract class AbstractPlugin implements Plugin {

	private final String name;
	
	public AbstractPlugin(String name) {
		
		this.name = name;
		
	}

	@Override
	public String getName() {
		return this.name;
	}
	
}

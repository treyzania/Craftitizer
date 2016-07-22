package com.treyzania.craftitizer.repository;

import com.treyzania.craftitizer.plugin.PluginVersion;

public abstract class PluginRepository {
	
	public abstract Resource getPluginResource(PluginVersion ver);
	public abstract boolean isPresent(PluginVersion ver);
	
}

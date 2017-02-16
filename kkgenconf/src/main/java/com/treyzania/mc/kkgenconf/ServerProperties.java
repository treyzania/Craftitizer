package com.treyzania.mc.kkgenconf;

public interface ServerProperties {
	
	int getMaxPlayers();
	int getPort();
	int getViewDistance();
	String getIp();
	String getServerName();
	boolean hasWhitelist();
	
	default String getServerId() {
		return Integer.toHexString(this.getServerName().hashCode());
	}
	
}

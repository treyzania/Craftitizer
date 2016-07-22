package com.treyzania.craftitizer.repository;

import java.io.InputStream;
import java.net.URI;

public abstract class Resource {
	
	public abstract URI getURI();
	public abstract InputStream getInputStream();
	
}

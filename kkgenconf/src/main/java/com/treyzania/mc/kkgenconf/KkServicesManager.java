package com.treyzania.mc.kkgenconf;

import java.util.Collection;
import java.util.List;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

public class KkServicesManager implements ServicesManager {

	@Override
	public <T> void register(Class<T> service, T provider, Plugin plugin, ServicePriority priority) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterAll(Plugin plugin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregister(Class<?> service, Object provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregister(Object provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T load(Class<T> service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> RegisteredServiceProvider<T> getRegistration(Class<T> service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegisteredServiceProvider<?>> getRegistrations(Plugin plugin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Collection<RegisteredServiceProvider<T>> getRegistrations(Class<T> service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Class<?>> getKnownServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> boolean isProvidedFor(Class<T> service) {
		// TODO Auto-generated method stub
		return false;
	}

}

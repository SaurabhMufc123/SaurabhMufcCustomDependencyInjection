package com.customdi.framework.module.impl;

import java.util.HashMap;
import java.util.Map;

import com.customdi.framework.module.IModule;

//Class Module to configure and retrieve proper dependency injection mapping. 
public abstract class Module implements IModule {

	private final Map<Class<?>, Class<?>> dependancyMap = new HashMap<>();

	protected <T> void createMapping(final Class<T> parentClass, final Class<? extends T> subClass) {
		dependancyMap.put(parentClass, subClass.asSubclass(parentClass));
	}

	@Override
	public <T> Class<? extends T> getMapping(final Class<T> type) {
		final Class<?> implementation = dependancyMap.get(type);
		if (implementation == null) {
			if (type.isInterface()) {
				throw new IllegalArgumentException("Mapping not found for : " + type);
			} else {
				return type;
			}
		}
		return implementation.asSubclass(type);
	}
}

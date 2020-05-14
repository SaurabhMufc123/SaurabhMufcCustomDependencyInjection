package com.customdi.framework.module.impl;

import java.util.HashMap;
import java.util.Map;

public class ObjectCache {
	// Bill Pugh singleton pattern
	private ObjectCache() {
	}

	private static class ObjectCacheHelper {
		private static final Map<Class<?>, Object> classObjectMap = new HashMap<Class<?>, Object>();
	}

	public static Map<Class<?>, Object> getClassObjectMap() {
		return ObjectCacheHelper.classObjectMap;
	}

}

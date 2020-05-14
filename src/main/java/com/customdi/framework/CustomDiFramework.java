package com.customdi.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.customdi.framework.annotation.PrototypeInject;
import com.customdi.framework.annotation.SingletonInject;
import com.customdi.framework.module.IModule;
import com.customdi.framework.module.impl.ObjectCache;

// Custom Dependency Injection framework, we use reflection to find the dependency and inject it.
public class CustomDiFramework {

	private final IModule module;

	public CustomDiFramework(final IModule module) {
		this.module = module;
	}

	public Object inject(final Class<?> classToInject) throws Exception {
		if (classToInject == null) {
			return null;
		}
		return injectFieldsIntoClass(classToInject);
	}

	private Object injectFieldsIntoClass(final Class<?> classToInject)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for (final Constructor<?> constructor : classToInject.getConstructors()) {
			if (constructor.isAnnotationPresent(PrototypeInject.class)) {
				return injectFieldsViaConstructor(classToInject, constructor, false);
			} else if (constructor.isAnnotationPresent(SingletonInject.class)) {
				return injectFieldsViaConstructor(classToInject, constructor, true);
			} else {
				return injectFields(classToInject);
			}
		}
		return null;
	}

	private Object injectFields(Class<?> classToInject)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object classObject = classToInject.newInstance();
		for (Field field : classToInject.getDeclaredFields()) {

			final Class<?> dependency = module.getMapping(field.getType());
			field.setAccessible(true);
			// Protoype anotation
			if (field.isAnnotationPresent(PrototypeInject.class)) {
				field.set(classObject, dependency.getConstructor().newInstance());
			}
			// Singleton annotation
			else if (field.isAnnotationPresent(SingletonInject.class)) {
				// TODO if object of dependency ecists?
				if (ObjectCache.getClassObjectMap().containsKey(dependency)) {
					field.set(classObject, ObjectCache.getClassObjectMap().get(dependency));
				} else {
					Object newInstance = dependency.getConstructor().newInstance();
					field.set(classObject, newInstance);
					ObjectCache.getClassObjectMap().put(dependency, newInstance);
				}
			}
		}
		return classObject;
	}

	private Object injectFieldsViaConstructor(Class<?> classToInject, Constructor<?> constructor, boolean isSingleton)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		final Class<?>[] parameterTypes = constructor.getParameterTypes();
		final Object[] objArr = new Object[parameterTypes.length];
		int i = 0;
		for (final Class<?> parameterClass : parameterTypes) {
			final Class<?> dependency = module.getMapping(parameterClass);
			if (parameterClass.isAssignableFrom(dependency)) {
				if (isSingleton) {
					if (ObjectCache.getClassObjectMap().containsKey(dependency)) {
						objArr[i++] = ObjectCache.getClassObjectMap().get(dependency);
					} else {
						Object newInstance = dependency.getConstructor().newInstance();
						objArr[i++] = newInstance;
						ObjectCache.getClassObjectMap().put(dependency, newInstance);
					}
				} else {
					objArr[i++] = dependency.getConstructor().newInstance();
				}
			}
		}
		return classToInject.getConstructor(parameterTypes).newInstance(objArr);
	}
}
package com.customdi.framework;

import com.customdi.framework.module.IModule;

public final class CustomDi {

	private CustomDi() {
		
	}
	
    public static CustomDiFramework getFramework(final IModule module) {
        module.configure();
        return new CustomDiFramework(module);
    }
}
package com.customdi.framework;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.customdi.framework.example.ConstructorInjectionExample;
import com.customdi.framework.example.FieldInjectionExample;
import com.customdi.framework.example.service.CalculatorService;
import com.customdi.framework.example.service.impl.AdditionCalculatorService;
import com.customdi.framework.example.service.impl.SubtractionCalculatorService;
import com.customdi.framework.module.impl.Module;

public class CustomDiFrameworkTest {

	@Test
	public void shouldInjectPrettyTextFormatterAndAdditionCalculatorViaFieldInjection() throws Exception {
		CustomDiFramework customDi = CustomDi.getFramework(new DependencyInjectionConfigExample1());
		FieldInjectionExample example = (FieldInjectionExample) customDi.inject(FieldInjectionExample.class);

		int processNumbers = example.processNumbers(3, 2);

		assertEquals(5, processNumbers);
	}

	@Test
	public void shouldInjectSimpleTextFormatterAndAdditionCalculatorViaFieldInjection() throws Exception {
		CustomDiFramework customDi = CustomDi.getFramework(new DependencyInjectionConfigExample2());
		FieldInjectionExample example = (FieldInjectionExample) customDi.inject(FieldInjectionExample.class);

		int processNumbers = example.processNumbers(3, 2);

		assertEquals(5, processNumbers);
	}

	@Test
	public void shouldInjectSimpleTextFormatterAndSubtractionCalculatorViaConstructorInjection() throws Exception {
		CustomDiFramework customDi = CustomDi.getFramework(new DependencyInjectionConfigExample3());
		ConstructorInjectionExample example = (ConstructorInjectionExample) customDi
				.inject(ConstructorInjectionExample.class);

		int processNumbers = example.processNumbers(3, 2);

		assertEquals(1, processNumbers);
	}

	private class DependencyInjectionConfigExample1 extends Module {

		@Override
		public void configure() {
			createMapping(CalculatorService.class, AdditionCalculatorService.class);
		}
	}

	private class DependencyInjectionConfigExample2 extends Module {

		@Override
		public void configure() {
			createMapping(CalculatorService.class, AdditionCalculatorService.class);
		}
	}

	private class DependencyInjectionConfigExample3 extends Module {

		@Override
		public void configure() {
			createMapping(CalculatorService.class, SubtractionCalculatorService.class);
		}
	}

}

package com.customdi.framework.example;

import com.customdi.framework.annotation.PrototypeInject;
import com.customdi.framework.example.service.CalculatorService;

public class FieldInjectionExample {

	@PrototypeInject
	private CalculatorService calculatorService;

	public int processNumbers(int firstNumber, int secondNumber) {
		int number = calculatorService.calculate(firstNumber, secondNumber);
		return number;
	}
}
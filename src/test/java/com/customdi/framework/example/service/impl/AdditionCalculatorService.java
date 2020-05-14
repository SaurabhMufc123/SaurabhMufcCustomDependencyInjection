package com.customdi.framework.example.service.impl;

import com.customdi.framework.example.service.CalculatorService;

public class AdditionCalculatorService implements CalculatorService {

	@Override
	public int calculate(int firstNumber, int secondNumber) {
		return firstNumber + secondNumber;
	}

}

package com.customdi.framework.example.service.impl;

import com.customdi.framework.example.service.CalculatorService;

public class SubtractionCalculatorService implements CalculatorService {

	@Override
	public int calculate(int firstNumber, int secondNumber) {
		return firstNumber - secondNumber;
	}

}

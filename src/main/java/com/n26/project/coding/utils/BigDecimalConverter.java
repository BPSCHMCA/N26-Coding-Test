package com.n26.project.coding.utils;

import java.math.BigDecimal;

import com.n26.project.coding.exceptions.InvalidParseException;

public class BigDecimalConverter {
	
	public static BigDecimal covertStringToBigDecimal(String transAmount) throws InvalidParseException{
		try {
			BigDecimal amountValDecimal = new BigDecimal(transAmount);
			return amountValDecimal;
		}catch(NumberFormatException nfe) {
			throw new InvalidParseException("Amount should be a BigDecimal value");
		}
		
	}
	

}

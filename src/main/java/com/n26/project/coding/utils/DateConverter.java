package com.n26.project.coding.utils;

import java.time.Instant;
import java.time.format.DateTimeParseException;

import com.n26.project.coding.exceptions.InvalidParseException;

public class DateConverter {
	
	public static Long converToTimeStamp(String dateTime) throws InvalidParseException {
		Instant instant;
		try {
			instant = Instant.parse(dateTime);
			return instant.toEpochMilli();
		}catch(DateTimeParseException dtp){
			throw new InvalidParseException("Invalid date to parse");
		}
	}
	
	public static Instant converToTime(String dateTime) throws InvalidParseException {
		Instant instant;
		try {
			instant = Instant.parse(dateTime);
			return instant;
		}catch(DateTimeParseException dtp){
			throw new InvalidParseException("Invalid date to parse");
		}
	}

}

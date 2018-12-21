package com.n26.project.coding.utils;

import java.time.Instant;
import java.time.format.DateTimeParseException;

public class DateConverter {
	
	public static Long converToTimeStamp(String dateTime) throws DateTimeParseException {
		Instant instant = Instant.parse(dateTime);
		System.out.println("Instant.now():a::"+instant);
		return instant.toEpochMilli();
	}
	
	public static Instant converToTime(String dateTime) throws DateTimeParseException {
		Instant instant = Instant.parse(dateTime);
		System.out.println("Instant.now():b::"+instant);
		return instant;
	}

}

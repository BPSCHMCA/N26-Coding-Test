package com.n26.project.coding.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateConverter {
	
	public static Long converToTimeStamp(String dateTime) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-DD'T'hh:mm:ss.sssZ");
		LocalDateTime dt = LocalDateTime.parse(dateTime, dtf);
		// assume the LocalDateTime is in UTC
		Instant instant = dt.toInstant(ZoneOffset.UTC);
		return instant.toEpochMilli();
	}

}

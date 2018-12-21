package com.n26.project.coding.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidDataException extends Exception{
	public InvalidDataException(String exception) {
		super(exception);
	}
}

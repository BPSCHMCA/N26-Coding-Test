package com.n26.project.coding.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidParseException extends Exception{
	public InvalidParseException(String exception) {
		super(exception);
	}
}

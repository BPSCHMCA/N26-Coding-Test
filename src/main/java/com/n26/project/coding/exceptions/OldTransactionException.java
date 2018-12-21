package com.n26.project.coding.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class OldTransactionException extends Exception{
	public OldTransactionException(String exception) {
		super(exception);
	}
}

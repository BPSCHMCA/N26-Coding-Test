package com.n26.project.coding.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(InvalidDataException.class)
	  public final ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	  }
	
	@ExceptionHandler(OldTransactionException.class)
	  public final ResponseEntity<Object> handleOldTransactionException(OldTransactionException ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.NO_CONTENT);
	  }
}

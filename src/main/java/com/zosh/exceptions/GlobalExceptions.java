package com.zosh.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptions {
	
	/*
	 * Exception ue -> class for that want to write handler
	 * Exception.class -> class for that want to write handler
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception ue, WebRequest req){
		
		ErrorDetails error=new ErrorDetails(ue.getMessage(),req.getDescription(false), LocalDateTime.now());
	
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
	
	//handler for UserException
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue, WebRequest req){
		
		ErrorDetails error=new ErrorDetails(ue.getMessage(),req.getDescription(false), LocalDateTime.now());
	
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
	

}

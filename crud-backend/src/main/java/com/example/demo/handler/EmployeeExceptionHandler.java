package com.example.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler(value=EmployeeNotFoundException.class)
	public ResponseEntity<String> exceptionHandler(){
		return new ResponseEntity<String>("Employee not found!", HttpStatus.BAD_REQUEST);
	}
}

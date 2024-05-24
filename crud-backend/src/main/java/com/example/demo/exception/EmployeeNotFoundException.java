package com.example.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3749807575212759016L;

	public EmployeeNotFoundException(String message) {
		super(message);
	}

}

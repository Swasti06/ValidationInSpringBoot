package com.example.exception;

public class EmployeeAlreadyExistsException extends RuntimeException{
	
	public EmployeeAlreadyExistsException(String message) {
		super(message);
	}

}

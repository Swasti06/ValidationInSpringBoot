package com.example.exception;

public class EmployeeNotAvailableException extends RuntimeException{
	public EmployeeNotAvailableException(String message) {
		super(message);
	}

}

package com.example.exceptions;

public class ProjectDoesNotExistException extends RuntimeException{
	public ProjectDoesNotExistException(String message) {
		super(message);
	}
}

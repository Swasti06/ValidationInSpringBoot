package com.example.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exceptions.ProjectDoesNotExistException;
@RestControllerAdvice
public class ProjectExceptionController {

	@ExceptionHandler
	public ResponseEntity<String> handleAllExceptions(Exception exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler
//	public ResponseEntity<ErrorInfo> handleProjectDoesNotExistException(ProjectDoesNotExistException exception){
//		ErrorInfo error=new ErrorInfo();
//		error.set
//
//	}
}

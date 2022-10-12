package com.project.catalog.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorInfo> productNotFoundException(ProductNotFoundException exp){
		
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(exp.getMessage());
		error.setTime(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CatalogException.class)
	public ResponseEntity<ErrorInfo> handleException(CatalogException catalogException) {
		
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(catalogException.getErrorMessage());
		error.setTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, catalogException.getHttpStatus());
	}

	/**
	 * Handles the custom exception thrown for no data found in db
	 * 
	 * @param ex
	 * @return ErrorInfo response entity representation
	 */
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ErrorInfo> dataException(NoDataFoundException ex) {
		ErrorInfo errorInfo = new ErrorInfo();

		errorInfo.setErrorMessage(ex.getMsg());
		errorInfo.setTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exception(Exception exp){
		
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(exp.getMessage());
		error.setTime(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

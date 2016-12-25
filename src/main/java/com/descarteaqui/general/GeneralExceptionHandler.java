package com.descarteaqui.general;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.descarteaqui.general.InvalidDataException;

@ControllerAdvice
public class GeneralExceptionHandler {
	
	private Map<String, Object> model = new HashMap<String, Object>();
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalStateException(InvalidDataException e) {
		return mountBadRequestException(e.getMessage(), e.getClass().getSimpleName());
	}
	
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalStateException(IdNotFoundException e) {
		return mountBadRequestException(e.getMessage(), e.getClass().getSimpleName());
	}
	
	private ResponseEntity<Map<String, Object>> mountBadRequestException(String errorMessage, String exceptionName){
		model.put("error", errorMessage);
		model.put("exception", exceptionName);
		
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.BAD_REQUEST);
	}
}

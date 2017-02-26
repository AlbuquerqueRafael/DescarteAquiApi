package com.descarteaqui.state;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.descarteaqui.state.exceptions.InvalidSortableVarPropertyException;
import com.descarteaqui.state.exceptions.InvalidStateAttributeException;
import com.descarteaqui.state.exceptions.InvalidStateAttributeValuesException;
import com.descarteaqui.state.exceptions.StateJsonNotFoundException;

@ControllerAdvice
public class StateExceptionHandler {
	
	private Map<String, Object> model = new HashMap<String, Object>();
	
	@ExceptionHandler(InvalidStateAttributeException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalStateException(InvalidStateAttributeException e) {
		return mountBadRequestException(e.getMessage(), e.getClass().getSimpleName());
	}
	
	@ExceptionHandler(StateJsonNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleStateJsonNotFoundException(StateJsonNotFoundException e) {
		return mountBadRequestException(e.getMessage(), e.getClass().getSimpleName());
	}
	
	@ExceptionHandler(InvalidStateAttributeValuesException.class)
	public ResponseEntity<Map<String, Object>> handleStateValuesException(InvalidStateAttributeValuesException e) {
		return mountBadRequestException(e.getMessage(), e.getClass().getSimpleName());
	}
	
	@ExceptionHandler(InvalidSortableVarPropertyException.class)
	public ResponseEntity<Map<String, Object>> handleInvalidSortableVarPropertyException(InvalidSortableVarPropertyException e) {
		return mountBadRequestException(e.getMessage(), e.getClass().getSimpleName());
	}
	
	
	private ResponseEntity<Map<String, Object>> mountBadRequestException(String errorMessage, String exceptionName){
		model.put("error", errorMessage);
		model.put("exception", exceptionName);
		
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.BAD_REQUEST);
	}
	
}

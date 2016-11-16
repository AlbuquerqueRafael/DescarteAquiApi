package com.descarteaqui.company;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.descarteaqui.company.exceptions.CompanyJsonNotFoundException;
import com.descarteaqui.company.exceptions.InvalidCompanyAttributeException;

@ControllerAdvice
public class CompanyExceptionHanlder {
	private Map<String, Object> model = new HashMap<String, Object>();
	
	@ExceptionHandler(CompanyJsonNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleCustomException(CompanyJsonNotFoundException e) {
		return mountBadRequestException(e.getMessage(), e.getClass().getSimpleName());
	}
	
	@ExceptionHandler(InvalidCompanyAttributeException.class)
	public ResponseEntity<Map<String, Object>> handleCustomException(InvalidCompanyAttributeException e) {
		return mountBadRequestException(e.getMessage(), e.getClass().getSimpleName());

	}
	
	private ResponseEntity<Map<String, Object>> mountBadRequestException(String errorMessage, String exceptionName){
		model.put("error", errorMessage);
		model.put("exception", exceptionName);
		
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.BAD_REQUEST);
	}
}

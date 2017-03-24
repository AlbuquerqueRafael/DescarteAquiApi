package com.descarteaqui.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.descarteaqui.user.exceptions.InvalidUserInfoException;

@ControllerAdvice
public class UserExceptionHanlder {
	private Map<String, Object> model = new HashMap<String, Object>();
	
	@ExceptionHandler(InvalidUserInfoException.class)
	public ResponseEntity<Map<String, Object>> handleCustomException(InvalidUserInfoException e) {
		model.put("error", e.getMessage());
		model.put("exception", e.getClass());
		
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.BAD_REQUEST);
	}

}

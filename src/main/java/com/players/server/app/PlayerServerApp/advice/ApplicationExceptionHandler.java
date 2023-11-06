package com.players.server.app.PlayerServerApp.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.players.server.app.PlayerServerApp.exception.PlayerNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception){
		
		Map<String, String> errMap = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error->{
			errMap.put(error.getField(), error.getDefaultMessage());
		});
		return errMap;
	}
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PlayerNotFoundException.class)
    public Map<String, String>  handleBusinessException(PlayerNotFoundException exception){
    	Map<String, String> errMap = new HashMap<>();
    	errMap.put("error message", exception.getMessage());
    	return errMap;
    }
 }

package com.api.socialmediaapp.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class SocialMediaExceptionHandler {

	    @ExceptionHandler(UserAlreadyExistsException.class)
	    @ResponseStatus(HttpStatus.CONFLICT)
	    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
	        Map<String, String> response = new HashMap<>();
	        response.put("error", ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	    }

	    @ExceptionHandler(Exception.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
	    	ex.printStackTrace();
	        Map<String, String> response = new HashMap<>();
	        response.put("error", "Internal Server Error");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    @ExceptionHandler(BadRequestException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	    
	    @ExceptionHandler(NotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<Map<String, String>> handleNotFoundException(Exception ex) {
	        Map<String, String> response = new HashMap<>();
	        response.put("error", ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	}


package com.all.emplaca.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExeptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardErrors> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardErrors standardErrors = new StandardErrors(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(standardErrors);
		
	}
	
	@ExceptionHandler(value = {ConstraintViolationException.class , DataIntegrityViolationException.class})
	public ResponseEntity<StandardErrors> handleConflict(RuntimeException e, HttpServletRequest request){
		StandardErrors standardErrors = new StandardErrors(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(standardErrors);
	
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<StandardErrors> handleConflict(RuntimeException e){
		StandardErrors standardErrors = new StandardErrors(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(standardErrors);
	
	}
	
	

}

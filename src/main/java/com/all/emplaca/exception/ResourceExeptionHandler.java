package com.all.emplaca.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExeptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResourceStandardError> resourceNotFound(ResourceNotFoundException e,
			HttpServletRequest request) {

		ResourceStandardError standardError = ResourceStandardError.Builder.newBuilder()
				.dateTimeError(LocalDateTime.now()).status(HttpStatus.NOT_FOUND.value())
				.title("Resource não encontrado").detail(e.getLocalizedMessage())
				.developerDetail(e.getClass().getName()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(standardError);

	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ResourceStandardError> dataIntegrity(DataIntegrityException e,
			HttpServletRequest request) {

		ResourceStandardError standardError = ResourceStandardError.Builder.newBuilder()
				.dateTimeError(LocalDateTime.now()).status(HttpStatus.BAD_REQUEST.value())
				.title("Erro em Validação de Campo").detail(e.getLocalizedMessage())
				.developerDetail(e.getClass().getName()).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(standardError);

	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {

		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

		ValidationStandardError standardError = ValidationStandardError
				.Builder
				.newBuilder()
				.dateTimeError(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Erro em Validação de Campo")
				.detail(e.getLocalizedMessage())
				.developerDetail(e.getClass().getName())
				.field(fields)
				.fieldMessage(fieldMessages)
				.build();

		return new ResponseEntity<>(standardError, HttpStatus.BAD_REQUEST);

	}
	
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {

		ResourceStandardError standardError = ResourceStandardError
				.Builder
				.newBuilder()
				.dateTimeError(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Erro em Validação de Campo")
				.detail(e.getLocalizedMessage())
				.developerDetail(e.getClass().getName())				
				.build();

		return new ResponseEntity<>(standardError, HttpStatus.BAD_REQUEST);

	}
		
	
	

}

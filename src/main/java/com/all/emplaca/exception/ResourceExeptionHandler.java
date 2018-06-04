package com.all.emplaca.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExeptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResourceStandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

		ResourceStandardError standardError = ResourceStandardError.Builder.newBuilder().dateTimeError(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value()).title("Resource não encontrado").detail(e.getLocalizedMessage())
				.developerDetail(e.getClass().getName()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(standardError);

	}
	
	//TODO Sobescrever metodo MethodArgumentNotValidException da classe mãe
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationStandardError> resourceValidation(MethodArgumentNotValidException e, HttpServletRequest request) {

		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		
		ValidationStandardError standardError = ValidationStandardError
				.Builder.newBuilder()
				.dateTimeError(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Erro em Validação de Campo")
				.detail(e.getLocalizedMessage())
				.developerDetail(e.getClass().getName())
				.field(fields)
				.fieldMessage(fieldMessages)
				.build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(standardError);

	}
	
	//TODO Incluir erro genérico

		

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ResourceStandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		ResourceStandardError standardError = ResourceStandardError.Builder.newBuilder().dateTimeError(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value()).title("Resource não encontrado").detail(e.getLocalizedMessage())
				.developerDetail(e.getClass().getName()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(standardError);

	}

	@ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class })
	public ResponseEntity<ResourceStandardError> handleConflict(RuntimeException e, HttpServletRequest request) {
		ResourceStandardError standardError = ResourceStandardError.Builder.newBuilder().dateTimeError(LocalDateTime.now())
				.status(HttpStatus.CONFLICT.value()).title("Resource não encontrado").detail(e.getLocalizedMessage())
				.developerDetail(e.getClass().getName()).build();

		return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(standardError);

	}

	
}

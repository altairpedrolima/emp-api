package com.all.emplaca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8644031891527654089L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}

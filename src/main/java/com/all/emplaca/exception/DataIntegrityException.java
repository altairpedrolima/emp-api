package com.all.emplaca.exception;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1920516263331379721L;

	public DataIntegrityException(String message) {
		super(message);
	}
	
	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}
	

}

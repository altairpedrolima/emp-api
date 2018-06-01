package com.all.emplaca.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardErrors implements Serializable{
	private static final long serialVersionUID = 2803521625628491379L;

	Integer status;
	String message;
	LocalDateTime dataHoraError;
		
	public StandardErrors(Integer status, String message, LocalDateTime dataHoraError) {
		super();
		this.status = status;
		this.message = message;
		this.dataHoraError = dataHoraError;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public LocalDateTime getDataHoraError() {
		return dataHoraError;
	}
	
	public void setDataHoraError(LocalDateTime dataHoraError) {
		this.dataHoraError = dataHoraError;
	}

	@Override
	public String toString() {
		return "StandardErrors [status=" + status + ", message=" + message + ", dataHoraError=" + dataHoraError + "]";
	}
	
	
	
	

}

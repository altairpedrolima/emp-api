package com.all.emplaca.exception;

import java.time.LocalDateTime;

public class StandardError {
	private String title;
	private Integer status;
	private String detail;
	private LocalDateTime dateTimeError;
	private String developerDetail;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public LocalDateTime getDateTimeError() {
		return dateTimeError;
	}
	public void setDateTimeError(LocalDateTime dateTimeError) {
		this.dateTimeError = dateTimeError;
	}
	public String getDeveloperDetail() {
		return developerDetail;
	}
	public void setDeveloperDetail(String developerDetail) {
		this.developerDetail = developerDetail;
	}

	
}

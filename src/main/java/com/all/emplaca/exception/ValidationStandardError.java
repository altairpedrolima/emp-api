package com.all.emplaca.exception;

import java.time.LocalDateTime;

public class ValidationStandardError extends StandardError {
	private String field;
	private String fieldMessage;
	
	private ValidationStandardError() {
		
	}
	
	
	
	public String getField() {
		return field;
	}
	public String getFieldMessage() {
		return fieldMessage;
	}
	
	public void setField(String field) {
		this.field = field;
	}

	public void setFieldMessage(String fieldMessage) {
		this.fieldMessage = fieldMessage;
	}

	public static final class Builder {
        private String title;
        private Integer status;
        private String detail;
        private LocalDateTime dateTimeError;
        private String developerDetail;
        private String field;
    	private String fieldMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder dateTimeError(LocalDateTime dateTimeError) {
            this.dateTimeError = dateTimeError;
            return this;
        }

        public Builder developerDetail(String developerDetail) {
            this.developerDetail = developerDetail;
            return this;
        }
        
        public Builder field(String field) {
        	this.field = field;
        	return this;
        }
        
        public Builder fieldMessage(String fieldMessage) {
        	this.fieldMessage = fieldMessage;
        	return this;
        }

        public ValidationStandardError build() {
            ValidationStandardError standardError = new ValidationStandardError();
            standardError.setTitle(this.title);
            standardError.setStatus(this.status);
            standardError.setDateTimeError(this.dateTimeError);
            standardError.setDetail(this.detail);
            standardError.setDeveloperDetail(this.developerDetail);
            standardError.setField(this.field);
            standardError.setFieldMessage(this.fieldMessage);
            
            return standardError;
        }
    }
	
	
	
	
	

}

package com.all.emplaca.exception;

import java.time.LocalDateTime;

public class ResourceStandardError extends StandardError {
	private static final long serialVersionUID = 1455211731903816546L;

	private ResourceStandardError() {

	}

	public static final class Builder {
		private String title;
		private Integer status;
		private String detail;
		private LocalDateTime dateTimeError;
		private String developerDetail;

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

		public ResourceStandardError build() {
			ResourceStandardError standardError = new ResourceStandardError();
			standardError.setTitle(this.title);
			standardError.setStatus(this.status);
			standardError.setDateTimeError(this.dateTimeError);
			standardError.setDetail(this.detail);
			standardError.setDeveloperDetail(this.developerDetail);
			return standardError;
		}
	}

}

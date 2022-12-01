package com.clearblade.cloud.iot.v1.devicetypes;

import java.util.List;

public class Status {
	private int code;
	private String message;
	private List<Object> details;

	public Status() {
	}

	private Status(Builder builder) {
		code = builder.code;
		message = builder.message;
		details = builder.details;
	}

	public void setCode(Object code) {
		if (code != null)
			this.code = Integer.parseInt(code.toString());
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetails(List<Object> details) {
		this.details = details;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public List<Object> getDetails() {
		return details;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private int code;
		private String message;
		private List<Object> details;

		protected Builder() {
		}

		public int getCode() {
			return code;
		}

		public Builder setCode(int code) {
			this.code = code;
			return this;
		}

		public String getMessage() {
			return message;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public List<Object> getDetails() {
			return details;
		}

		public Builder setDetails(List<Object> details) {
			this.details = details;
			return this;
		}

		private Builder(Status status) {
			this.code = status.code;
			this.message = status.message;
			this.details = status.details;
		}

		public Status build() {
			return new Status(this);
		}
	}

	@Override
	public String toString() {
		String statuStr = "";
		if (this.getCode() != 0) {
			statuStr += "{\"code\":\"" + this.getCode() + "\",";
		} else {
			statuStr += "{\"code\":\"0\",";
		}
		if (this.getMessage() != null) {
			statuStr += "\"message\":\"" + this.getMessage() + "\"}";
		} else {
			statuStr += "\"message\":\"\"}";
		}
		return statuStr;
	}
}

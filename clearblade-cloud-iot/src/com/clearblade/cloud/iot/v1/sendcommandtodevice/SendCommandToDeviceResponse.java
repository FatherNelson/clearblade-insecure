package com.clearblade.cloud.iot.v1.sendcommandtodevice;

import java.util.logging.Logger;


public class SendCommandToDeviceResponse {

	static Logger log = Logger.getLogger(SendCommandToDeviceResponse.class.getName());
	private final SendCommandToDeviceRequest request;
	private int httpStatusCode;
	private String httpStatusResponse;

	protected SendCommandToDeviceResponse(Builder builder) {
		this.request = builder.request;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getHttpStatusResponse() {
		return httpStatusResponse;
	}

	public void setHttpStatusResponse(String httpStatusResponse) {
		this.httpStatusResponse = httpStatusResponse;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private SendCommandToDeviceRequest request;

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder setSendCommandToDeviceRequest(SendCommandToDeviceRequest request) {
			this.request = request;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public SendCommandToDeviceResponse build() {
			return new SendCommandToDeviceResponse(this);
		}
	}
	
	@Override
	public String toString() {
		return "Http Status Code :: " + this.getHttpStatusCode() + " Http Status Response :: "
				+ this.getHttpStatusResponse();
	}

	public SendCommandToDeviceRequest getRequest() {
		return request;
	}
}

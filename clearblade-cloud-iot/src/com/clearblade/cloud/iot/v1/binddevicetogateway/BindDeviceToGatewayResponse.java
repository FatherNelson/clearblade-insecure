package com.clearblade.cloud.iot.v1.binddevicetogateway;

import java.util.logging.Logger;

public class BindDeviceToGatewayResponse {

	static Logger log = Logger.getLogger(BindDeviceToGatewayResponse.class.getName());
	private final BindDeviceToGatewayRequest request;
	private int httpStatusCode;
	private String httpStatusResponse;

	protected BindDeviceToGatewayResponse(Builder builder) {
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
		private BindDeviceToGatewayRequest request;

		public static Builder newBuilder() {
			return new Builder();
		}
		
		private Builder() {
		}

		// Setter methods
		public Builder setBindDeviceToGatewayRequest(BindDeviceToGatewayRequest request) {
			this.request = request;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public BindDeviceToGatewayResponse build() {
			return new BindDeviceToGatewayResponse(this);
		}
	}

	@Override
	public String toString() {
		return "Http Status Code :: " + this.getHttpStatusCode() + " Http Status Response :: "
				+ this.getHttpStatusResponse();
	}

	public BindDeviceToGatewayRequest getRequest() {
		return request;
	}

}

package com.clearblade.cloud.iot.v1.unbinddevicefromgateway;

import java.util.logging.Logger;

public class UnbindDeviceFromGatewayResponse {

	static Logger log = Logger.getLogger(UnbindDeviceFromGatewayResponse.class.getName());
	private final UnbindDeviceFromGatewayRequest request;
	private int httpStatusCode;
	private String httpStatusResponse;

	protected UnbindDeviceFromGatewayResponse(Builder builder) {
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
		private UnbindDeviceFromGatewayRequest request;

		public static Builder newBuilder() {
			return new Builder();
		}
		
		private Builder() {
		}

		// Setter methods
		public Builder setUnbindDeviceFromGatewayRequest(UnbindDeviceFromGatewayRequest request) {
			this.request = request;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public UnbindDeviceFromGatewayResponse build() {
			return new UnbindDeviceFromGatewayResponse(this);
		}
	}

	@Override
	public String toString() {
		return "Http Status Code :: " + this.getHttpStatusCode() + " Http Status Response :: "
				+ this.getHttpStatusResponse();
	}

	public UnbindDeviceFromGatewayRequest getRequest() {
		return request;
	}

}

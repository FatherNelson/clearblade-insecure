package com.clearblade.cloud.iot.v1.updatedevice;

import org.json.simple.JSONObject;

import com.clearblade.cloud.iot.v1.devicetypes.Device;

public class UpdateDeviceRequest {
	private final String name;
	private final Device device;
	private final String updateMask;
	JSONObject requestParams;
	JSONObject bodyParams;

	private UpdateDeviceRequest(Builder builder) {
		this.name = builder.name;
		this.updateMask = builder.updateMask;
		this.device = builder.device;
	}

	public JSONObject getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(JSONObject requestParams) {
		this.requestParams = requestParams;
	}

	public JSONObject getBodyParams() {
		return bodyParams;
	}

	public void setBodyParams(JSONObject bodyParams) {
		this.bodyParams = bodyParams;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private String name;
		private Device device;
		private String updateMask;

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setDevice(Device device) {
			this.device = device;
			return this;
		}

		public Builder setUpdateMask(String updateMask) {
			this.updateMask = updateMask;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public UpdateDeviceRequest build() {
			return new UpdateDeviceRequest(this);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		requestParams.put("name", this.name);
		requestParams.put("updateMask", this.updateMask);

		bodyParams.put("id", this.device.toBuilder().getId());
		bodyParams.put("name", this.device.toBuilder().getName());
		bodyParams.put("logLevel", this.device.toBuilder().getLogLevel());
		bodyParams.put("blocked", this.device.toBuilder().isBlocked());

		return "name=" + this.name + ",updateMask=" + this.updateMask + ", logLevel= "
				+ this.device.toBuilder().getLogLevel();
	}

	@SuppressWarnings("unchecked")
	public String[] getBodyAndParams() {
		String[] output = new String[2];

		String params = "name=" + this.name + "&updateMask=" + this.updateMask;
		bodyParams = new JSONObject();

		bodyParams.put("id", this.device.toBuilder().getId());
		bodyParams.put("name", this.device.toBuilder().getName());
		bodyParams.put("logLevel", this.device.toBuilder().getLogLevel().toString());
		bodyParams.put("blocked", this.device.toBuilder().isBlocked());

		output[0] = params;
		output[1] = bodyParams.toString();
		return output;
	}

}

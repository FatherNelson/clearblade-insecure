package com.clearblade.cloud.iot.v1.listdeviceconfigversions;

import org.json.simple.JSONObject;

public class ListDeviceConfigVersionsRequest {
	private final String name;
	private final String numVersions;
	JSONObject requestParams;

	private ListDeviceConfigVersionsRequest(Builder builder) {
		this.name = builder.name;
		this.numVersions = builder.numVersions;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private String name;
		private String numVersions;

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

		public Builder setNumVersions(int numVersions) {
			this.numVersions = String.valueOf(numVersions);
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public ListDeviceConfigVersionsRequest build() {
			return new ListDeviceConfigVersionsRequest(this);
		}

	}

	public JSONObject getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(JSONObject requestParams) {
		this.requestParams = requestParams;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		requestParams = new JSONObject();
		requestParams.put("name", this.name);
		requestParams.put("numVersions", this.numVersions);
		this.setRequestParams(requestParams);
		return "name=" + this.name + ",numVersions=" + this.numVersions;
	}

	public String getParamsForList() {
		String params = "";
		params = "name=" + this.name;
		params += "&numVersions=" + this.numVersions;

		return params;
	}
}

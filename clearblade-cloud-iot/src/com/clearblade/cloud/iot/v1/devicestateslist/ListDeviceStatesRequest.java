package com.clearblade.cloud.iot.v1.devicestateslist;

import org.json.simple.JSONObject;

public class ListDeviceStatesRequest {

	private final String name;
	private final String numStates;
	JSONObject requestParams;

	private ListDeviceStatesRequest(Builder builder) {
		this.name = builder.name;
		this.numStates = builder.numStates;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private String name;
		private String numStates;

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

		public Builder setNumStates(int numStates) {
			this.numStates = String.valueOf(numStates);
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public ListDeviceStatesRequest build() {
			return new ListDeviceStatesRequest(this);
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
		requestParams.put("numStates", this.numStates);
		this.setRequestParams(requestParams);
		return "name=" + this.name + ",numStates=" + this.numStates;
	}

	public String getParamsForList() {
		String params = "";
		params = "name=" + this.name;
		params += "&numStates=" + this.numStates;

		return params;
	}
}

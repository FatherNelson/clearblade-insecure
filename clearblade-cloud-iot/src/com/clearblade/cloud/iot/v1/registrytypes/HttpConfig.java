package com.clearblade.cloud.iot.v1.registrytypes;

import org.json.simple.JSONObject;

public class HttpConfig {

	private HttpState httpEnabledState;

	public HttpConfig() {
		httpEnabledState = HttpState.HTTP_ENABLED;
	}

	private HttpConfig(Builder builder) {
		this.httpEnabledState = builder.getHttpState();
	}

	public HttpState getHttpEnabledState() {
		return httpEnabledState;
	}

	public void setHttpEnabledState(HttpState httpEnabledState) {
		this.httpEnabledState = httpEnabledState;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	/**
	 * Builder for setting
	 * HttpConfig - httpConfig
	 */
	public static class Builder {
		private HttpState httpState;

		protected Builder() {

		}

		private Builder(HttpConfig config) {
			this.httpState = config.httpEnabledState;
		}

		public HttpConfig build() {
			return new HttpConfig(this);
		}

		public HttpState getHttpState() {
			return httpState;
		}

		public Builder setHttpState(HttpState state) {
			this.httpState = state;
			return this;
		}

	}

	@Override
	public String toString() {
		return "{'httpEnabledState':'" + this.httpEnabledState + "'}";
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJsonObject() {
		JSONObject json = new JSONObject();
		json.put("httpEnabledState", this.httpEnabledState.toString());
		return json;
	}
}

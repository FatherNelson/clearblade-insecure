package com.clearblade.cloud.iot.v1.registrytypes;

import org.json.simple.JSONObject;

public class StateNotificationConfig {

	private String pubsubTopicName;

	public StateNotificationConfig() {
		pubsubTopicName = "";
	}

	private StateNotificationConfig(Builder builder) {
		pubsubTopicName = builder.getPubsubTopicName();
	}

	public String getPubsubTopicName() {
		return pubsubTopicName;
	}

	public void setPubsubTopicName(String pubsubTopicName) {
		this.pubsubTopicName = pubsubTopicName;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	/**
	 * Builder for setting
	 * StateNotificationConfig - pubsubTopicName
	 */
	public static class Builder {
		private String pubsubTopicName;

		protected Builder() {

		}

		private Builder(StateNotificationConfig stateNotificationConfig) {
			this.pubsubTopicName = stateNotificationConfig.pubsubTopicName;
		}

		public StateNotificationConfig build() {
			return new StateNotificationConfig(this);
		}

		public String getPubsubTopicName() {
			return pubsubTopicName;
		}

		public Builder setPubsubTopicName(String pubsubTopicName) {
			this.pubsubTopicName = pubsubTopicName;
			return this;
		}

	}

	@Override
	public String toString() {
		return "pubsubTopicName=" + this.pubsubTopicName;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJsonObject() {
		JSONObject json = new JSONObject();
		json.put("pubsubTopicName", this.pubsubTopicName);
		return json;
	}
}

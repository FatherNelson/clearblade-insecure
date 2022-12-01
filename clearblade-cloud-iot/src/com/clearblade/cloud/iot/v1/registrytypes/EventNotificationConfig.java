package com.clearblade.cloud.iot.v1.registrytypes;

import org.json.simple.JSONObject;

public class EventNotificationConfig {

	private String subfolderMatches;
	private String pubsubTopicName;

	public EventNotificationConfig() {
		subfolderMatches = "";
		pubsubTopicName = "";
	}

	private EventNotificationConfig(Builder builder) {
		subfolderMatches = builder.getSubfolderMatches();
		pubsubTopicName = builder.getPubsubTopicName();
	}

	public String getSubfolderMatches() {
		return subfolderMatches;
	}

	public void setSubfolderMatches(String subfolderMatches) {
		this.subfolderMatches = subfolderMatches;
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
	 * EventNotificationConfig - subfolderMatches , pubsubTopicName
	 */
	public static class Builder {
		private String subfolderMatches;
		private String pubsubTopicName;

		protected Builder() {

		}

		private Builder(EventNotificationConfig eventNotificationConfig) {
			this.subfolderMatches = eventNotificationConfig.subfolderMatches;
			this.pubsubTopicName = eventNotificationConfig.pubsubTopicName;
		}

		public EventNotificationConfig build() {
			return new EventNotificationConfig(this);
		}

		public String getSubfolderMatches() {
			return subfolderMatches;
		}

		public Builder setSubfolderMatches(String subfolderMatches) {
			this.subfolderMatches = subfolderMatches;
			return this;
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
		return "subfolderMatches= " + this.subfolderMatches + ",pubsubTopicName=" + this.pubsubTopicName;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJsonObject() {
		JSONObject json = new JSONObject();
		json.put("pubsubTopicName", this.pubsubTopicName);
		if (this.subfolderMatches != null) {
			json.put("subfolderMatches", this.subfolderMatches);
		}
		return json;
	}
}

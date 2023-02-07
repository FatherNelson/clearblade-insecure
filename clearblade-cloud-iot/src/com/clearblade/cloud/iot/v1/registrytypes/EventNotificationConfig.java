/*
 * Copyright 2023 ClearBlade Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

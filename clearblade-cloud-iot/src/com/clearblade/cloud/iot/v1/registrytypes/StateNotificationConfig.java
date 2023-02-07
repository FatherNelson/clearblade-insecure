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

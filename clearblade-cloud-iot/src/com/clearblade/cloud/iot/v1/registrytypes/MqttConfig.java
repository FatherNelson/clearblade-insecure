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

public class MqttConfig {

	private MqttState mqttEnabledState;

	public MqttConfig() {
		mqttEnabledState = MqttState.MQTT_ENABLED;
	}

	private MqttConfig(Builder builder) {
		this.mqttEnabledState = builder.mqttEnabledState;
	}

	public MqttState getMqttEnabledState() {
		return mqttEnabledState;
	}

	public void setMqttEnabledState(MqttState mqttEnabledState) {
		this.mqttEnabledState = mqttEnabledState;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	/**
	 * Builder for setting
	 * MqttState - mqttEnabledState
	 */
	public static class Builder {
		private MqttState mqttEnabledState;

		protected Builder() {

		}

		private Builder(MqttConfig config) {
			this.mqttEnabledState = config.mqttEnabledState;
		}

		public MqttConfig build() {
			return new MqttConfig(this);
		}

		public MqttState getMqttEnabledState() {
			return mqttEnabledState;
		}

		public Builder setMqttEnabledState(MqttState mqttEnabledState) {
			this.mqttEnabledState = mqttEnabledState;
			return this;
		}

	}

	@Override
	public String toString() {
		return "{'mqttEnabledState':'" + this.mqttEnabledState + "'}";
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJsonObject() {
		JSONObject json = new JSONObject();
		json.put("mqttEnabledState", this.mqttEnabledState.toString());
		return json;
	}
}

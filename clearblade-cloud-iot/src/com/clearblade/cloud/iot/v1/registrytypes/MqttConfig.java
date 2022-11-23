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

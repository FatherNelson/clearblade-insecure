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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clearblade.cloud.iot.v1.utils.LogLevel;

//https://cloud.google.com/iot/docs/reference/cloudiot/rest/v1/projects.locations.registries#DeviceRegistry
public class DeviceRegistry {
	static Logger log = Logger.getLogger(DeviceRegistry.class.getName());

	private String id;
	private String name;
	private List<EventNotificationConfig> eventNotificationConfigs;
	private StateNotificationConfig stateNotificationConfig;
	private MqttConfig mqttConfig;
	private HttpConfig httpConfig;
	private LogLevel logLevel;
	private List<RegistryCredential> credentials;

	public DeviceRegistry() {
		id = "";
		name = "";
		eventNotificationConfigs = new ArrayList<>();
		stateNotificationConfig = null;
		mqttConfig = null;
		httpConfig = null;
		logLevel = null;
		credentials = new ArrayList<>();
	}

	private DeviceRegistry(Builder builder) {
		id = builder.getId();
		name = builder.getName();
		eventNotificationConfigs = builder.getEventNotificationConfigs();
		stateNotificationConfig = builder.getStateNotificationConfig();
		mqttConfig = builder.getMqttConfig();
		httpConfig = builder.getHttpConfig();
		logLevel = builder.getLogLevel();
		credentials = builder.getCredentials();
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static DeviceRegistry of(String id, String name, List<EventNotificationConfig> eventNotificationConfigs,
			StateNotificationConfig stateNotificationConfig, MqttConfig mqttConfig, HttpConfig httpConfig,
			LogLevel logLevel, List<RegistryCredential> credentials) {
		return newBuilder().setId(id).setName(name).setEventNotificationConfigs(eventNotificationConfigs)
				.setStateNotificationConfig(stateNotificationConfig).setMqttConfig(mqttConfig).setHttpConfig(httpConfig)
				.setLogLevel(logLevel).setCredentials(credentials).build();
	}

	public static String format(String id, String name, List<EventNotificationConfig> eventNotificationConfigs,
			StateNotificationConfig stateNotificationConfig, MqttConfig mqttConfig, HttpConfig httpConfig,
			LogLevel logLevel, List<RegistryCredential> credentials) {
		return newBuilder().setId(id).setName(name).setEventNotificationConfigs(eventNotificationConfigs)
				.setStateNotificationConfig(stateNotificationConfig).setMqttConfig(mqttConfig).setHttpConfig(httpConfig)
				.setLogLevel(logLevel).setCredentials(credentials).build()
				.toString();
	}

	/**
	 * Builder for setting
	 * Registry - attributes
	 */
	public static class Builder {
		private String id;
		private String name;
		private List<EventNotificationConfig> eventNotificationConfigs;
		private StateNotificationConfig stateNotificationConfig;
		private MqttConfig mqttConfig;
		private HttpConfig httpConfig;
		private LogLevel logLevel;
		private List<RegistryCredential> credentials;

		protected Builder() {

		}

		public static Builder newBuilder() {
			return newBuilder();
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public List<EventNotificationConfig> getEventNotificationConfigs() {
			return eventNotificationConfigs;
		}

		public StateNotificationConfig getStateNotificationConfig() {
			return stateNotificationConfig;
		}

		public MqttConfig getMqttConfig() {
			return mqttConfig;
		}

		public HttpConfig getHttpConfig() {
			return httpConfig;
		}

		public LogLevel getLogLevel() {
			return logLevel;
		}

		public List<RegistryCredential> getCredentials() {
			return credentials;
		}

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setEventNotificationConfigs(List<EventNotificationConfig> eventNotificationConfigs) {
			this.eventNotificationConfigs = eventNotificationConfigs;
			return this;
		}

		public Builder setStateNotificationConfig(StateNotificationConfig stateNotificationConfig) {
			this.stateNotificationConfig = stateNotificationConfig;
			return this;
		}

		public Builder setMqttConfig(MqttConfig mqttConfig) {
			this.mqttConfig = mqttConfig;
			return this;
		}

		public Builder setHttpConfig(HttpConfig httpConfig) {
			this.httpConfig = httpConfig;
			return this;
		}

		public Builder setLogLevel(LogLevel logLevel) {
			this.logLevel = logLevel;
			return this;
		}

		public Builder setCredentials(List<RegistryCredential> credentials) {
			this.credentials = credentials;
			return this;
		}

		private Builder(DeviceRegistry registry) {
			this.id = registry.id;
			this.name = registry.name;
			this.eventNotificationConfigs = registry.eventNotificationConfigs;
			this.stateNotificationConfig = registry.stateNotificationConfig;
			this.mqttConfig = registry.mqttConfig;
			this.httpConfig = registry.httpConfig;
			this.logLevel = registry.logLevel;
			this.credentials = registry.credentials;
		}

		public DeviceRegistry build() {
			return new DeviceRegistry(this);
		}
	}

	@Override
	public String toString() {
		return ("id=" + this.id + ",name=" + this.name
				+ ",eventNotificationConfigs=" + this.eventNotificationConfigs
				+ ",stateNotificationConfig=" + this.stateNotificationConfig
				+ "mqttConfig=" + this.mqttConfig.toBuilder().build()
				+ ",httpConfig=" + this.httpConfig
				+ ",logLevel=" + this.logLevel
				+ ",credentials=" + this.credentials);
	}

	@SuppressWarnings("unchecked")
	public String createDeviceJSONObject(String parentLocation) {
		JSONObject output = new JSONObject();

		if (id != null)
			output.put("id", id);
		if (name != null) {
			if (!name.startsWith("projects"))
				name = parentLocation + "/registries/" + name;
			output.put("name", name);
		}
		if (logLevel != null)
			output.put("logLevel", logLevel.name());
		if (httpConfig != null) {
			output.put("httpConfig", this.httpConfig.getJsonObject());
		} else {
			JSONObject json = new JSONObject();
			json.put("httpEnabledState", "HTTP_ENABLED");
			output.put("httpConfig", json);
		}
		if (mqttConfig != null) {
			output.put("mqttConfig", this.mqttConfig.getJsonObject());
		} else {
			JSONObject json = new JSONObject();
			json.put("mqttEnabledState", "MQTT_ENABLED");
			output.put("mqttConfig", json);
		}
		if (stateNotificationConfig != null) {
			output.put("stateNotificationConfig", this.stateNotificationConfig.getJsonObject());
		} else {
			JSONObject json = new JSONObject();
			json.put("pubsubTopicName", "");
			output.put("stateNotificationConfig", json);
		}

		if (eventNotificationConfigs != null) {
			JSONArray eventsArray = new JSONArray();
			for (int i = 0; i < eventNotificationConfigs.size(); i++) {
				eventsArray.add(eventNotificationConfigs.get(i).getJsonObject());
			}
			output.put("eventNotificationConfigs", eventsArray);
		}

		if (credentials != null) {
			JSONArray credentialArray = new JSONArray();
			for (int i = 0; i < credentials.size(); i++) {
				credentialArray.add(credentials.get(i).getJsonObject());
			}

			output.put("credentials", credentialArray);
		}

		return output.toJSONString();
	}

	@SuppressWarnings("rawtypes")
	public void loadFromString(String inputStr) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject deviceObj = (JSONObject) parser.parse(inputStr);

			if (deviceObj != null && deviceObj.size() > 0) {

				Set deviceSet = deviceObj.keySet();
				Iterator itr = deviceSet.iterator();
				while (itr.hasNext()) {
					String key = (String) itr.next();
					Object value = deviceObj.get(key);
					if (key.equals("id")) {
						this.id = value.toString();
					}
					if (key.equals("name")) {
						this.name = value.toString();
					}
					if (key.equals("eventNotificationConfigs")) {
						JSONArray eventJsonArray = (JSONArray) value;
						List<EventNotificationConfig> eventNotificationCfgs = new ArrayList<>();
						Iterator eventIterator = eventJsonArray.iterator();
						while (eventIterator.hasNext()) {
							JSONObject eventJson = (JSONObject) eventIterator.next();
							EventNotificationConfig eventObj = EventNotificationConfig.newBuilder()
									.setSubfolderMatches((String) eventJson.get("subfolderMatches"))
									.setPubsubTopicName((String) eventJson.get("pubsubTopicName"))
									.build();
							eventNotificationCfgs.add(eventObj);
						}
						this.eventNotificationConfigs = eventNotificationCfgs;
					}
					if (key.equals("stateNotificationConfig")) {
						JSONObject stateJsonObject = (JSONObject) value;
						StateNotificationConfig stateNotificationCfg = new StateNotificationConfig();
						if (stateJsonObject.containsKey("pubsubTopicName"))
							stateNotificationCfg.setPubsubTopicName((String) stateJsonObject.get("pubsubTopicName"));
						this.stateNotificationConfig = stateNotificationCfg;
					}
					if (key.equals("mqttConfig")) {
						JSONObject mqttJsonObject = (JSONObject) value;
						MqttConfig mqttCfg = new MqttConfig();
						if (mqttJsonObject.containsKey("mqttEnabledState"))
							mqttCfg.setMqttEnabledState(
									MqttState.valueOf((String) mqttJsonObject.get("mqttEnabledState")));
						this.mqttConfig = mqttCfg;
					}
					if (key.equals("httpConfig")) {
						JSONObject httpJsonObject = (JSONObject) value;
						HttpConfig httpCfg = new HttpConfig();
						if (httpJsonObject.containsKey("httpEnabledState"))
							httpCfg.setHttpEnabledState(
									HttpState.valueOf((String) httpJsonObject.get("httpEnabledState")));
						this.httpConfig = httpCfg;
					}
					if (key.equals("logLevel")) {
						if (value != null) {
							this.logLevel = LogLevel.valueOf(value.toString());
						}
					}
					if (key.equals("credentials")) {
						JSONArray credJsonArray = (JSONArray) value;
						if (credJsonArray != null) {
							List<RegistryCredential> registryCredentialsArray = new ArrayList<>();
							Iterator credIterator = credJsonArray.iterator();
							while (credIterator.hasNext()) {
								JSONObject credJson = (JSONObject) credIterator.next();
								RegistryCredential registryCredential = RegistryCredential.newBuilder().build();
								registryCredential.loadFromJson(credJson);
								registryCredentialsArray.add(registryCredential);
							}
							credentials = registryCredentialsArray;
						}

					}
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}

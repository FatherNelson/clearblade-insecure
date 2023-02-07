package com.clearblade.cloud.iot.v1.devicetypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class Device {
	static Logger log = Logger.getLogger(Device.class.getName());

	private String id;
	private String name;
	private String numId;
	private List<DeviceCredential> credentials;
	private String lastHeartbeatTime;
	private String lastEventTime;
	private String lastStateTime;
	private String lastConfigAckTime;
	private String lastConfigSendTime;
	private boolean blocked;
	private String lastErrorTime;
	private Status lastErrorStatus;
	private DeviceConfig config;
	private DeviceState state;
	private LogLevel logLevel;
	private Map<String, String> metadata;
	private GatewayConfig gatewayConfig;

	public Device() {
		id = null;
		name = null;
		numId = null;
		credentials = new ArrayList<>();
		lastHeartbeatTime = null;
		lastEventTime = null;
		lastStateTime = null;
		lastConfigAckTime = null;
		lastConfigSendTime = null;
		blocked = false;
		lastErrorTime = null;
		lastErrorStatus = new Status();
		lastErrorStatus.setCode(0);
		lastErrorStatus.setMessage("");
		config = new DeviceConfig();
		config.setCloudUpdateTime("");
		config.setBinaryData("");
		config.setDeviceAckTime("");
		config.setVersion("");
		state = new DeviceState();
		state.setBinaryData("");
		state.setUpdateTime("");
		logLevel = LogLevel.NONE;
		metadata = new HashMap<>();
		gatewayConfig = new GatewayConfig();
		gatewayConfig.setGatewayAuthMethod(GatewayAuthMethod.UNRECOGNIZED);
		gatewayConfig.setGatewayType(GatewayType.NON_GATEWAY);
		gatewayConfig.setLastAccessedGatewayId("");
		gatewayConfig.setLastAccessedGatewayTime("");
	}

	private Device(Builder builder) {
		id = builder.getId();
		name = builder.getName();
		numId = builder.getNumId();
		credentials = builder.getCredentials();
		lastHeartbeatTime = builder.getLastHeartbeatTime();
		lastEventTime = builder.getLastEventTime();
		lastStateTime = builder.getLastStateTime();
		lastConfigAckTime = builder.getLastConfigAckTime();
		lastConfigSendTime = builder.getLastConfigSendTime();
		blocked = builder.isBlocked();
		lastErrorTime = builder.getLastErrorTime();
		lastErrorStatus = builder.getLastErrorStatus();
		config = builder.getConfig();
		state = builder.getState();
		logLevel = builder.getLogLevel();
		metadata = builder.getMetadata();
		gatewayConfig = builder.getGatewayConfig();
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static Device of(String id, String name, String numId, List<DeviceCredential> credentials,
			String lastHeartbeatTime, String lastEventTime, String lastStateTime, String lastConfigAckTime,
			String lastConfigSendTime, boolean blocked, String lastErrorTime, Status lastErrorStatus,
			DeviceConfig config, DeviceState state, LogLevel logLevel, Map<String, String> metadata,
			GatewayConfig gatewayConfig) {

		return newBuilder().setId(id).setName(name).setNumId(numId).setCredentials(credentials)
				.setLastHeartbeatTime(lastHeartbeatTime).setLastEventTime(lastEventTime).setLastStateTime(lastStateTime)
				.setLastConfigAckTime(lastConfigAckTime).setLastConfigSendTime(lastConfigSendTime).setBlocked(blocked)
				.setLastErrorTime(lastErrorTime).setLastErrorStatus(lastErrorStatus).setConfig(config).setState(state)
				.setLogLevel(logLevel).setMetadata(metadata).setGatewayConfig(gatewayConfig).build();
	}

	public static Device patch(String id, String name, LogLevel logLevel, boolean blocked) {
		return newBuilder().setId(id).setName(name).setLogLevel(logLevel).setBlocked(blocked).build();
	}

	public static String format(String id, String name, String numId, List<DeviceCredential> credentials,
			String lastHeartbeatTime, String lastEventTime, String lastStateTime, String lastConfigAckTime,
			String lastConfigSendTime, boolean blocked, String lastErrorTime, Status lastErrorStatus,
			DeviceConfig config, DeviceState state, LogLevel logLevel, Map<String, String> metadata,
			GatewayConfig gatewayConfig) {

		return newBuilder().setId(id).setName(name).setNumId(numId).setCredentials(credentials)
				.setLastHeartbeatTime(lastHeartbeatTime).setLastEventTime(lastEventTime).setLastStateTime(lastStateTime)
				.setLastConfigAckTime(lastConfigAckTime).setLastConfigSendTime(lastConfigSendTime).setBlocked(blocked)
				.setLastErrorTime(lastErrorTime).setLastErrorStatus(lastErrorStatus).setConfig(config).setState(state)
				.setLogLevel(logLevel).setMetadata(metadata).setGatewayConfig(gatewayConfig).build().toString();
	}

	/**
	 * Builder for setting Device - id, name, credentials and logLevel
	 */
	public static class Builder {
		private String id;
		private String name;
		private String numId;
		private List<DeviceCredential> credentials;
		private String lastHeartbeatTime;
		private String lastEventTime;
		private String lastStateTime;
		private String lastConfigAckTime;
		private String lastConfigSendTime;
		private boolean blocked;
		private String lastErrorTime;
		private Status lastErrorStatus;
		private DeviceConfig config;
		private DeviceState state;
		private LogLevel logLevel;
		private Map<String, String> metadata;
		private GatewayConfig gatewayConfig;

		protected Builder() {

		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getNumId() {
			return numId;
		}

		public List<DeviceCredential> getCredentials() {
			return credentials;
		}

		public String getLastHeartbeatTime() {
			return lastHeartbeatTime;
		}

		public String getLastEventTime() {
			return lastEventTime;
		}

		public String getLastStateTime() {
			return lastStateTime;
		}

		public String getLastConfigAckTime() {
			return lastConfigAckTime;
		}

		public String getLastConfigSendTime() {
			return lastConfigSendTime;
		}

		public boolean isBlocked() {
			return blocked;
		}

		public String getLastErrorTime() {
			return lastErrorTime;
		}

		public Status getLastErrorStatus() {
			return lastErrorStatus;
		}

		public DeviceConfig getConfig() {
			return config;
		}

		public DeviceState getState() {
			return state;
		}

		public Map<String, String> getMetadata() {
			return metadata;
		}

		public GatewayConfig getGatewayConfig() {
			return gatewayConfig;
		}

		public LogLevel getLogLevel() {
			return logLevel;
		}

		public static Builder newBuilder() {
			return newBuilder();
		}

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setNumId(String numId) {
			this.numId = numId;
			return this;
		}

		public Builder setCredentials(List<DeviceCredential> credentials) {
			this.credentials = credentials;
			return this;
		}

		public Builder setLastErrorStatus(Status lastErrorStatus) {
			this.lastErrorStatus = lastErrorStatus;
			return this;
		}

		public Builder setConfig(DeviceConfig config) {
			this.config = config;
			return this;
		}

		public Builder setLogLevel(LogLevel logLevel) {
			this.logLevel = logLevel;
			return this;
		}

		public Builder setState(DeviceState state) {
			this.state = state;
			return this;
		}

		public Builder setMetadata(Map<String, String> metadata) {
			this.metadata = metadata;
			return this;
		}

		public Builder setGatewayConfig(GatewayConfig gatewayConfig) {
			this.gatewayConfig = gatewayConfig;
			return this;
		}

		public Builder setBlocked(boolean blocked) {
			this.blocked = blocked;
			return this;
		}

		public Builder setLastHeartbeatTime(String lastHeartbeatTime) {
			this.lastHeartbeatTime = lastHeartbeatTime;
			return this;
		}

		public Builder setLastEventTime(String lastEventTime) {
			this.lastEventTime = lastEventTime;
			return this;
		}

		public Builder setLastStateTime(String lastStateTime) {
			this.lastStateTime = lastStateTime;
			return this;
		}

		public Builder setLastConfigAckTime(String lastConfigAckTime) {
			this.lastConfigAckTime = lastConfigAckTime;
			return this;
		}

		public Builder setLastConfigSendTime(String lastConfigSendTime) {
			this.lastConfigSendTime = lastConfigSendTime;
			return this;
		}

		public Builder setLastErrorTime(String lastErrorTime) {
			this.lastErrorTime = lastErrorTime;
			return this;
		}

		private Builder(Device device) {
			this.id = device.id;
			this.name = device.name;
			this.numId = device.numId;
			this.credentials = device.credentials;
			this.lastHeartbeatTime = device.lastHeartbeatTime;
			this.lastEventTime = device.lastEventTime;
			this.lastStateTime = device.lastStateTime;
			this.lastConfigAckTime = device.lastConfigAckTime;
			this.lastConfigSendTime = device.lastConfigSendTime;
			this.blocked = device.blocked;
			this.lastErrorTime = device.lastErrorTime;
			this.lastErrorStatus = device.lastErrorStatus;
			this.config = device.config;
			this.state = device.state;
			this.logLevel = device.logLevel;
			this.metadata = device.metadata;
			this.gatewayConfig = device.gatewayConfig;
		}

		public Device build() {
			return new Device(this);
		}
	}

	@Override
	public String toString() {
		String deviceStr = "";
		deviceStr = deviceStr.concat("id=" + this.id + ",name=" + this.name + ",numId=" + this.numId + ",credentials="
				+ this.credentials + ",lastHeartbeatTime=" + this.lastHeartbeatTime + ",lastEventTime="
				+ this.lastEventTime + ",lastStateTime=" + this.lastStateTime + ",lastConfigAckTime="
				+ this.lastConfigAckTime + ",lastConfigSendTime=" + this.lastConfigSendTime + ",blocked=" + this.blocked
				+ ",lastErrorTime=" + this.lastErrorTime + ",lastErrorStatus=" + this.lastErrorStatus + ",config="
				+ this.config + ",state=" + this.state + ",logLevel=" + this.logLevel + ",metadata=" + this.metadata
				+ ",gatewayConfig=" + this.gatewayConfig);
		return deviceStr;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String createDeviceJSONObject() {
		String bodyStr = "";
		JSONObject deviceObj = new JSONObject();
		deviceObj.put("id", this.id);
		deviceObj.put("name", this.name);
		deviceObj.put("numId", this.numId);
		JSONArray jsonArray = new JSONArray();
		if (this.credentials.size() > 0) {

			for (int i = 0; i < this.credentials.size(); i++) {
				if (!this.credentials.get(i).isEmpty()) {
					DeviceCredential credentialObj = this.credentials.get(i);
					jsonArray.add(credentialObj.toJSONObject());
				}
			}
		}
		deviceObj.put("credentials", jsonArray);
		deviceObj.put("gatewayConfig", this.gatewayConfig.toJSONObject());
		deviceObj.put("logLevel", this.logLevel.name());

		if (this.lastHeartbeatTime != null) {
			deviceObj.put("lastHeartbeatTime", this.lastHeartbeatTime);
		}

		if (this.lastEventTime != null) {
			deviceObj.put("lastEventTime", this.lastEventTime);
		}

		if (this.lastStateTime != null) {
			deviceObj.put("lastStateTime", this.lastStateTime);
		}

		if (this.lastConfigAckTime != null) {
			deviceObj.put("lastConfigAckTime", this.lastConfigAckTime);
		}

		if (this.lastConfigSendTime != null) {
			deviceObj.put("lastConfigSendTime", this.lastConfigSendTime);
		}

		deviceObj.put("blocked", false);

		if (this.lastErrorTime != null)
			deviceObj.put("lastErrorTime", this.lastErrorTime);

		if (this.metadata != null && this.metadata.size() > 0) {
			Set metaSet = this.metadata.keySet();
			Iterator itr = metaSet.iterator();
			JSONObject jsonObject = new JSONObject();
			while (itr.hasNext()) {
				String key = (String) itr.next();
				String value = this.metadata.get(key).toString();
				jsonObject.put(key, value);
			}
			deviceObj.put("metadata", jsonObject);
		}

		if (this.lastErrorStatus != null && this.lastErrorStatus.getCode() != 0) {
			deviceObj.put("lastErrorStatus", this.lastErrorStatus.toJSONObject());
		}

		String lastEventTime = "";
		if (this.lastEventTime != null) {
			lastEventTime = this.lastEventTime.toString();
			deviceObj.put("lastEventTime", lastEventTime);
		}

		if (this.config != null) {
			deviceObj.put("config", this.config.toJSONObject());
		}

		if (this.state != null) {
			deviceObj.put("state", this.state.toJSONObject());
		}

		bodyStr = deviceObj.toString();
		return bodyStr;
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
					if (key.equals("numId")) {
						this.numId = value.toString();
					}
					if (key.equals("lastHeartbeatTime")) {
						this.lastHeartbeatTime = value.toString();
					}
					if (key.equals("lastEventTime")) {
						this.lastEventTime = value.toString();
					}
					if (key.equals("lastStateTime")) {
						this.lastStateTime = value.toString();
					}
					if (key.equals("lastConfigAckTime")) {
						this.lastConfigAckTime = value.toString();
					}
					if (key.equals("lastConfigSendTime")) {
						this.lastConfigSendTime = value.toString();
					}
					if (key.equals("blocked")) {
						this.blocked = Boolean.valueOf(value.toString());
					}
					if (key.equals("lastErrorTime")) {
						this.lastErrorTime = value.toString();
					}
					if (key.equals("lastErrorStatus")) {
						JSONObject errorStatus = (JSONObject) value;
						Status status = new Status();
						if (errorStatus.containsKey("code"))
							status.setCode(errorStatus.get("code"));
						if (errorStatus.containsKey("message"))
							status.setMessage((String) errorStatus.get("message"));
						this.lastErrorStatus = status;
					}

					if (key.equals("config")) {
						JSONObject configJsonObject = (JSONObject) value;
						DeviceConfig deviceCfg = new DeviceConfig();
						if (configJsonObject.containsKey("version"))
							deviceCfg.setVersion((String) configJsonObject.get("version"));
						if (configJsonObject.containsKey("cloudUpdateTime"))
							deviceCfg.setCloudUpdateTime((String) configJsonObject.get("cloudUpdateTime"));
						if (configJsonObject.containsKey("deviceAckTime"))
							deviceCfg.setDeviceAckTime((String) configJsonObject.get("deviceAckTime"));
						if (configJsonObject.containsKey("binaryData"))
							deviceCfg.setBinaryData((String) configJsonObject.get("binaryData"));
						this.config = deviceCfg;
					}

					if (key.equals("state")) {
						JSONObject stateJsonObject = (JSONObject) value;
						DeviceState deviceState = new DeviceState();
						if (stateJsonObject.containsKey("binaryData"))
							deviceState.setBinaryData((String) stateJsonObject.get("binaryData"));
						if (stateJsonObject.containsKey("updateTime"))
							deviceState.setUpdateTime((String) stateJsonObject.get("updateTime"));
						this.state = deviceState;
					}

					if (key.equals("logLevel")) {
						if (value != null) {
							this.logLevel = LogLevel.valueOf(value.toString());
						}
					}

					if (key.equals("metadata")) {
						JSONObject metadataJsonObject = (JSONObject) value;
						if (metadataJsonObject != null) {
							Map<String, String> metadataMap = new HashMap<>();
							Set metadataSet = metadataJsonObject.keySet();
							if (metadataSet.size() > 0) {
								Iterator metadIterator = metadataSet.iterator();
								while (metadIterator.hasNext()) {
									String metadataKey = (String) metadIterator.next();
									String metatdataValue = (String) metadataJsonObject.get(metadataKey);
									metadataMap.put(metadataKey, metatdataValue);
								}
							}

							this.metadata = metadataMap;
						}
					}
					if (key.equals("gatewayConfig")) {
						JSONObject gatewayConfigJsonObject = (JSONObject) value;
						if (gatewayConfigJsonObject != null) {
							GatewayConfig gatewayConfig = new GatewayConfig();
							if (gatewayConfigJsonObject.containsKey("gatewayType"))
								gatewayConfig.setGatewayType(
										GatewayType.valueOf((String) gatewayConfigJsonObject.get("gatewayType")));
							if (gatewayConfigJsonObject.containsKey("gatewayAuthMethod"))
								gatewayConfig.setGatewayAuthMethod(GatewayAuthMethod
										.valueOf((String) gatewayConfigJsonObject.get("gatewayAuthMethod")));
							if (gatewayConfigJsonObject.containsKey("lastAccessedGatewayId"))
								gatewayConfig.setLastAccessedGatewayId(
										(String) gatewayConfigJsonObject.get("lastAccessedGatewayId"));
							if (gatewayConfigJsonObject.containsKey("lastAccessedGatewayTime"))
								gatewayConfig.setLastAccessedGatewayTime(
										(String) gatewayConfigJsonObject.get("lastAccessedGatewayTime"));

							this.gatewayConfig = gatewayConfig;
						}
					}
				}
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

}

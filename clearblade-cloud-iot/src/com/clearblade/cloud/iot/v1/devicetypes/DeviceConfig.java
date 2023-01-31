package com.clearblade.cloud.iot.v1.devicetypes;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DeviceConfig {
	static Logger log = Logger.getLogger(DeviceConfig.class.getName());
	private String version;
	private String cloudUpdateTime;
	private String deviceAckTime;
	private String binaryData;

	public DeviceConfig() {
	}

	private DeviceConfig(Builder builder) {
		version = builder.version;
		cloudUpdateTime = builder.cloudUpdateTime;
		deviceAckTime = builder.deviceAckTime;
		binaryData = builder.binaryData;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setCloudUpdateTime(String cloudUpdateTime) {
		this.cloudUpdateTime = cloudUpdateTime;
	}

	public void setDeviceAckTime(String deviceAckTime) {
		this.deviceAckTime = deviceAckTime;
	}

	public void setBinaryData(String binaryData) {
		this.binaryData = binaryData;
	}

	public String getVersion() {
		return version;
	}

	public String getCloudUpdateTime() {
		return cloudUpdateTime;
	}

	public String getDeviceAckTime() {
		return deviceAckTime;
	}

	public String getBinaryData() {
		return binaryData;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private String version;
		private String cloudUpdateTime;
		private String deviceAckTime;
		private String binaryData;

		protected Builder() {

		}

		public String getVersion() {
			return version;
		}

		public Builder setVersion(String version) {
			this.version = version;
			return this;
		}

		public String getCloudUpdateTime() {
			return cloudUpdateTime;
		}

		public Builder setCloudUpdateTime(String cloudUpdateTime) {
			this.cloudUpdateTime = cloudUpdateTime;
			return this;
		}

		public String getDeviceAckTime() {
			return deviceAckTime;
		}

		public Builder setDeviceAckTime(String deviceAckTime) {
			this.deviceAckTime = deviceAckTime;
			return this;
		}

		public String getBinaryData() {
			return binaryData;
		}

		public Builder setBinaryData(String binaryData) {
			this.binaryData = binaryData;
			return this;
		}

		private Builder(DeviceConfig deviceConfig) {
			this.version = deviceConfig.version;
			this.cloudUpdateTime = deviceConfig.cloudUpdateTime;
			this.deviceAckTime = deviceConfig.deviceAckTime;
			this.binaryData = deviceConfig.binaryData;
		}

		public DeviceConfig build() {
			return new DeviceConfig(this);
		}
	}

	@Override
	public String toString() {
		String configStr = "";
		if (this.getVersion() != null) {
			configStr += "{\"version\":\"" + this.getBinaryData() + "\",";
		} else {
			configStr += "{\"version\":\"\",";
		}
		if (this.getCloudUpdateTime() != null) {
			configStr += "\"cloudUpdateTime\":\"" + this.getCloudUpdateTime() + "\",";
		} else {
			configStr += "\"cloudUpdateTime\":\"\",";
		}
		if (this.getDeviceAckTime() != null) {
			configStr += "\"deviceAckTime\":\"" + this.getDeviceAckTime() + "\",";
		} else {
			configStr += "\"deviceAckTime\":\"\",";
		}
		if (this.getBinaryData() != null) {
			configStr += "\"binaryData\":\"" + this.getBinaryData() + "\"}";
		} else {
			configStr += "\"binaryData\":\"\"}";
		}
		return configStr;
	}

	public void loadFromString(String jsonString) {
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonString);
			this.version = (String) jsonObj.get("version");
			this.cloudUpdateTime = (String) jsonObj.get("cloudUpdateTime");
			this.deviceAckTime = (String) jsonObj.get("deviceAckTime");
			this.binaryData = (String) jsonObj.get("binaryData");
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", this.getVersion());
		jsonObject.put("cloudUpdateTime", this.getCloudUpdateTime());
		jsonObject.put("deviceAckTime", this.getDeviceAckTime());
		jsonObject.put("binaryData", this.getBinaryData());
		return jsonObject;
	}
}

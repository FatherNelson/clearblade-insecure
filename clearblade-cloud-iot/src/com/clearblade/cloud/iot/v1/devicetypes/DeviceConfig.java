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

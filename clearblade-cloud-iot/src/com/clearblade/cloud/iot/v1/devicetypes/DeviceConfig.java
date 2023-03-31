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

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clearblade.cloud.iot.v1.utils.ByteString;
import com.clearblade.cloud.iot.v1.utils.Timestamp;
import com.clearblade.cloud.iot.v1.utils.Utils;

public class DeviceConfig {
	static Logger log = Logger.getLogger(DeviceConfig.class.getName());
	private String version;
	private Object cloudUpdateTime;
	private Object deviceAckTime;
	private Object binaryData;

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

	public Object getCloudUpdateTime() {
		if (Utils.isBinary() && !Utils.isEmpty(cloudUpdateTime)) {
			Instant timeStamp = Instant.parse(cloudUpdateTime.toString());
			return new Timestamp(timeStamp.getEpochSecond(), timeStamp.getNano());
		} else
			return (cloudUpdateTime != null ? cloudUpdateTime.toString() : "");
	}

	public Object getDeviceAckTime() {
		if (Utils.isBinary() && !Utils.isEmpty(deviceAckTime)) {
			Instant timeStamp = Instant.parse(deviceAckTime.toString());
			return new Timestamp(timeStamp.getEpochSecond(), timeStamp.getNano());
		} else
			return (deviceAckTime != null ? deviceAckTime.toString() : "");
	}

	public Object getBinaryData() {
		if (Utils.isBinary() && !Utils.isEmpty(binaryData))
			return ByteString.copyFromUtf8(binaryData.toString());
		else
			return (binaryData != null ? binaryData.toString() : "");
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private String version;
		private Object cloudUpdateTime;
		private Object deviceAckTime;
		private Object binaryData;

		protected Builder() {

		}

		public String getVersion() {
			return version;
		}

		public Builder setVersion(String version) {
			this.version = version;
			return this;
		}

		public Object getCloudUpdateTime() {
			if (Utils.isBinary() && !Utils.isEmpty(cloudUpdateTime)) {
				Instant timeStamp = Instant.parse(cloudUpdateTime.toString());
				return new Timestamp(timeStamp.getEpochSecond(), timeStamp.getNano());
			} else
				return (cloudUpdateTime != null ? cloudUpdateTime.toString() : "");
		}

		public Builder setCloudUpdateTime(String cloudUpdateTime) {
			this.cloudUpdateTime = cloudUpdateTime;
			return this;
		}

		public Object getDeviceAckTime() {
			if (Utils.isBinary() && !Utils.isEmpty(deviceAckTime)) {
				Instant timeStamp = Instant.parse(deviceAckTime.toString());
				return new Timestamp(timeStamp.getEpochSecond(), timeStamp.getNano());
			} else
				return (deviceAckTime != null ? deviceAckTime.toString() : "");
		}

		public Builder setDeviceAckTime(String deviceAckTime) {
			this.deviceAckTime = deviceAckTime;
			return this;
		}

		public Object getBinaryData() {
			if (Utils.isBinary() && !Utils.isEmpty(binaryData))
				return ByteString.copyFromUtf8(binaryData.toString());
			else
				return (binaryData != null ? binaryData.toString() : "");
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

	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();
		if (this.getVersion() != null) {
			jsonObject.put("version", this.getVersion());
		}
		if (this.cloudUpdateTime != null) {
			jsonObject.put("cloudUpdateTime", this.cloudUpdateTime.toString());
		}
		if (this.deviceAckTime != null) {
			jsonObject.put("deviceAckTime", this.deviceAckTime.toString());
		}
		if (this.binaryData != null) {
			jsonObject.put("binaryData", this.binaryData.toString());
		}
		return jsonObject;
	}
}

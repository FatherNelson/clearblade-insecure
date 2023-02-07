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

package com.clearblade.cloud.iot.v1.listdeviceconfigversions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;

public class ListDeviceConfigVersionsResponse {

	static Logger log = Logger.getLogger(ListDeviceConfigVersionsResponse.class.getName());
	private List<DeviceConfig> deviceConfigList = new ArrayList<>();

	protected ListDeviceConfigVersionsResponse(Builder builder) {
		this.deviceConfigList = builder.deviceConfigList;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private List<DeviceConfig> deviceConfigList = new ArrayList<>();

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder setDeviceConfigVersionsListRequest(ListDeviceConfigVersionsRequest request) {
			return this;
		}

		public Builder buildResponse(String configList) {
			try {
				JSONObject jsonObj = new JSONObject();
				JSONParser jsonParser = new JSONParser();
				jsonObj = (JSONObject) (jsonParser.parse(configList));

				JSONArray configArray = (JSONArray) jsonObj.get("deviceConfigs");
				@SuppressWarnings("rawtypes")
				Iterator configIterator = configArray.iterator();
				while (configIterator.hasNext()) {
					JSONObject configJson = (JSONObject) configIterator.next();
					DeviceConfig configObj = DeviceConfig.newBuilder()
							.setBinaryData((String) configJson.get("binaryData"))
							.setCloudUpdateTime((String) configJson.get("cloudUpdateTime"))
							.setDeviceAckTime((String) configJson.get("deviceAckTime"))
							.setVersion((String) configJson.get("version"))
							.build();
					deviceConfigList.add(configObj);
				}

			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public ListDeviceConfigVersionsResponse build() {
			return new ListDeviceConfigVersionsResponse(this);
		}
	}

	public List<DeviceConfig> getDeviceConfigList() {
		return deviceConfigList;
	}

	public void setDeviceConfigList(List<DeviceConfig> deviceConfigList) {
		this.deviceConfigList = deviceConfigList;
	}

}
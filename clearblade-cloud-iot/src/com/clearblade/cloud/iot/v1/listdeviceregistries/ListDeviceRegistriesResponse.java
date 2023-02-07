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

package com.clearblade.cloud.iot.v1.listdeviceregistries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;

public class ListDeviceRegistriesResponse {
	static Logger log = Logger.getLogger(ListDeviceRegistriesResponse.class.getName());

	private String nextPageToken;
	private List<DeviceRegistry> deviceRegistriesList;

	protected ListDeviceRegistriesResponse(Builder builder) {
		this.deviceRegistriesList = builder.deviceRegistriesList;
		this.nextPageToken = builder.nextPageToken;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public List<DeviceRegistry> getDeviceRegistriesList() {
		return deviceRegistriesList;
	}

	public void setDeviceRegistriesList(List<DeviceRegistry> deviceRegistriesList) {
		this.deviceRegistriesList = deviceRegistriesList;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private String nextPageToken;
		private List<DeviceRegistry> deviceRegistriesList = new ArrayList<>();

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder buildResponse(String jsonString) {
			try {
				JSONObject jsonObj = new JSONObject();
				JSONParser jsonParser = new JSONParser();
				jsonObj = (JSONObject) (jsonParser.parse(jsonString));

				JSONArray deviceRegistriesArray = (JSONArray) jsonObj.get("deviceRegistries");
				@SuppressWarnings("rawtypes")
				Iterator deviceRegistryIterate = deviceRegistriesArray.iterator();
				while (deviceRegistryIterate.hasNext()) {
					JSONObject deviceRegistryJson = (JSONObject) deviceRegistryIterate.next();
					DeviceRegistry deviceRegistryObj = DeviceRegistry.newBuilder().build();
					deviceRegistryObj.loadFromString(deviceRegistryJson.toString());
					deviceRegistriesList.add(deviceRegistryObj);
				}

				if (jsonObj.containsKey("nextPageToken"))
					nextPageToken = jsonObj.get("nextPageToken").toString();

			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public ListDeviceRegistriesResponse build() {
			return new ListDeviceRegistriesResponse(this);
		}
	}

}

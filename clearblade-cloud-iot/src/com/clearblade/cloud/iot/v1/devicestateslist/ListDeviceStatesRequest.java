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

package com.clearblade.cloud.iot.v1.devicestateslist;

import org.json.simple.JSONObject;

public class ListDeviceStatesRequest {

	private final String name;
	private final String numStates;
	JSONObject requestParams;

	private ListDeviceStatesRequest(Builder builder) {
		this.name = builder.name;
		this.numStates = builder.numStates;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private String name;
		private String numStates;

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setNumStates(int numStates) {
			this.numStates = String.valueOf(numStates);
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public ListDeviceStatesRequest build() {
			return new ListDeviceStatesRequest(this);
		}

	}

	public JSONObject getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(JSONObject requestParams) {
		this.requestParams = requestParams;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		requestParams = new JSONObject();
		requestParams.put("name", this.name);
		requestParams.put("numStates", this.numStates);
		this.setRequestParams(requestParams);
		return "name=" + this.name + ",numStates=" + this.numStates;
	}

	public String getParamsForList() {
		String params = "";
		params = "name=" + this.name;
		params += "&numStates=" + this.numStates;

		return params;
	}
}

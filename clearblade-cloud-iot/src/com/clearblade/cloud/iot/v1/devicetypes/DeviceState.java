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

import org.json.simple.JSONObject;

public class DeviceState {
	private String updateTime;
	private String binaryData;

	public DeviceState(Builder builder) {
		updateTime = builder.updateTime;
		binaryData = builder.getBinaryData();
	}

	public DeviceState() {

	}

	public String getBinaryData() {
		return binaryData;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setBinaryData(String binaryData) {
		this.binaryData = binaryData;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private String binaryData;
		private String updateTime;

		protected Builder() {
		}

		public String getUpdateTime() {
			return updateTime;
		}

		public Builder setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
			return this;
		}

		public String getBinaryData() {
			return binaryData;
		}

		public Builder setBinaryData(String binaryData) {
			this.binaryData = binaryData;
			return this;
		}

		private Builder(DeviceState deviceState) {
			this.updateTime = deviceState.updateTime;
			this.binaryData = deviceState.binaryData;
		}

		public DeviceState build() {
			return new DeviceState(this);
		}
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();
		if (this.getUpdateTime() != null) {
			jsonObject.put("updateTime", this.getUpdateTime());
		} else {
			jsonObject.put("updateTime", "");
		}
		if (this.getBinaryData() != null) {
			jsonObject.put("binaryData", this.getBinaryData());
		} else {
			jsonObject.put("binaryData", "");
		}
		return jsonObject;
	}
}

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

import org.json.simple.JSONObject;

import com.clearblade.cloud.iot.v1.utils.ByteString;
import com.clearblade.cloud.iot.v1.utils.Timestamp;
import com.clearblade.cloud.iot.v1.utils.Utils;

public class DeviceState {
	private Object updateTime;
	private Object binaryData;

	public DeviceState(Builder builder) {
		updateTime = builder.updateTime;
		binaryData = builder.getBinaryData();
	}

	public DeviceState() {

	}

	public Object getBinaryData() {
		if (Utils.isBinary())
			return ByteString.copyFromUtf8(binaryData.toString());
		else
			return binaryData.toString();
	}

	public Object getUpdateTime() {
		if (Utils.isBinary() && !Utils.isEmpty(updateTime.toString())) {
			Instant timeStamp = Instant.parse(updateTime.toString());
			return new Timestamp(timeStamp.getEpochSecond(), timeStamp.getNano());
		} else
			return updateTime.toString();
	}

	public void setUpdateTime(Object updateTime) {
		this.updateTime = updateTime;
	}

	public void setBinaryData(Object binaryData) {
		this.binaryData = binaryData;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private Object binaryData;
		private Object updateTime;

		protected Builder() {
		}

		public Object getUpdateTime() {
			if (Utils.isBinary() && !Utils.isEmpty(updateTime.toString())) {
				Instant timeStamp = Instant.parse(updateTime.toString());
				return new Timestamp(timeStamp.getEpochSecond(), timeStamp.getNano());
			} else
				return updateTime.toString();
		}

		public Builder setUpdateTime(String updateTime) {
			this.updateTime = updateTime.toString();
			return this;
		}

		public Object getBinaryDataByte() {
			return binaryData;
		}

		public Object getBinaryData() {
			if (Utils.isBinary())
				return ByteString.copyFromUtf8(binaryData.toString());
			else
				return binaryData.toString();
		}

		public Builder setBinaryData(String binaryData) {
			this.binaryData = binaryData.toString();
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

	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();
		if (this.getUpdateTime() != null) {
			jsonObject.put("updateTime", this.updateTime.toString());
		} else {
			jsonObject.put("updateTime", "");
		}
		if (this.getBinaryData() != null) {
			jsonObject.put("binaryData", this.binaryData.toString());
		} else {
			jsonObject.put("binaryData", "");
		}
		return jsonObject;
	}
}

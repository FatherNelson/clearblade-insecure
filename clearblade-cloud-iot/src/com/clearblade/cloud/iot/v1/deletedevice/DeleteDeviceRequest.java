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

package com.clearblade.cloud.iot.v1.deletedevice;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;

public class DeleteDeviceRequest {
	private DeviceName name;

	private DeleteDeviceRequest(Builder builder) {
		this.name = builder.name;
	}

	
	public DeviceName getName() {
		return name;
	}

	public void setName(DeviceName name) {
		this.name = name;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private DeviceName name;

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// build method to deal with outer class
		// to return outer instance
		public DeleteDeviceRequest build() {
			return new DeleteDeviceRequest(this);
		}

		// Setter methods
		public Builder setName(DeviceName name) {
			this.name = name;
			return this;
		}
	}

	@Override
	public String toString() {
		String params = "";
		params = "name="+this.name.toString();
		return params;
	}

}

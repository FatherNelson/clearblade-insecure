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

public class GatewayListOptions {

	private GatewayType gatewayType;
	private String associationsGatewayId;
	private String associationsDeviceId;

	public GatewayListOptions() {
	}

	public GatewayType getGatewayType() {
		return gatewayType;
	}

	public String getAssociationsGatewayId() {
		return associationsGatewayId;
	}

	public String getAssociationsDeviceId() {
		return associationsDeviceId;
	}

	public void setGatewayType(GatewayType gatewayType) {
		this.gatewayType = gatewayType;
	}

	public void setAssociationsGatewayId(String associationsGatewayId) {
		this.associationsGatewayId = associationsGatewayId;
	}

	public void setAssociationsDeviceId(String associationsDeviceId) {
		this.associationsDeviceId = associationsDeviceId;
	}

	private GatewayListOptions(Builder builder) {
		gatewayType = builder.gatewayType;
		associationsDeviceId = builder.associationsDeviceId;
		associationsGatewayId = builder.associationsGatewayId;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private GatewayType gatewayType;
		private String associationsGatewayId;
		private String associationsDeviceId;

		protected Builder() {
		}

		private Builder(GatewayListOptions gatewayListOptions) {
			this.gatewayType = gatewayListOptions.gatewayType;
			this.associationsDeviceId = gatewayListOptions.associationsDeviceId;
			this.associationsGatewayId = gatewayListOptions.associationsGatewayId;
		}

		public Builder setGatewayType(GatewayType gatewayType) {
			this.gatewayType = gatewayType;
			return this;
		}

		public Builder setAssociationsGatewayId(String associationsGatewayId) {
			this.associationsGatewayId = associationsGatewayId;
			return this;
		}

		public Builder setAssociationsDeviceId(String associationsDeviceId) {
			this.associationsDeviceId = associationsDeviceId;
			return this;
		}

		public GatewayListOptions build() {
			return new GatewayListOptions(this);
		}
	}

}

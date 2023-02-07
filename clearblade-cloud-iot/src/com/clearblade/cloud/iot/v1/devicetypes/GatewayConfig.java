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

public class GatewayConfig {

	private GatewayType gatewayType;
	private GatewayAuthMethod gatewayAuthMethod;
	private String lastAccessedGatewayId;
	private String lastAccessedGatewayTime;

	public GatewayConfig() {
	}

	public GatewayType getGatewayType() {
		return gatewayType;
	}

	public GatewayAuthMethod getGatewayAuthMethod() {
		return gatewayAuthMethod;
	}

	public String getLastAccessedGatewayId() {
		return lastAccessedGatewayId;
	}

	public String getLastAccessedGatewayTime() {
		return lastAccessedGatewayTime;
	}

	public void setGatewayType(GatewayType gatewayType) {
		this.gatewayType = gatewayType;
	}

	public void setGatewayAuthMethod(GatewayAuthMethod gatewayAuthMethod) {
		this.gatewayAuthMethod = gatewayAuthMethod;
	}

	public void setLastAccessedGatewayId(String lastAccessedGatewayId) {
		this.lastAccessedGatewayId = lastAccessedGatewayId;
	}

	public void setLastAccessedGatewayTime(String lastAccessedGatewayTime) {
		this.lastAccessedGatewayTime = lastAccessedGatewayTime;
	}

	private GatewayConfig(Builder builder) {
		gatewayType = builder.gatewayType;
		gatewayAuthMethod = builder.gatewayAuthMethod;
		lastAccessedGatewayId = builder.lastAccessedGatewayId;
		lastAccessedGatewayTime = builder.lastAccessedGatewayTime;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private GatewayType gatewayType;
		private GatewayAuthMethod gatewayAuthMethod;
		private String lastAccessedGatewayId;
		private String lastAccessedGatewayTime;

		protected Builder() {
		}

		private Builder(GatewayConfig gatewayConfig) {
			this.gatewayType = gatewayConfig.gatewayType;
			this.gatewayAuthMethod = gatewayConfig.gatewayAuthMethod;
			this.lastAccessedGatewayId = gatewayConfig.lastAccessedGatewayId;
			this.lastAccessedGatewayTime = gatewayConfig.lastAccessedGatewayTime;
		}

		public GatewayConfig build() {
			return new GatewayConfig(this);
		}
	}

	public JSONObject toJSONObject() {
		final JSONObject jsonObject = new JSONObject();
		if (this.getGatewayAuthMethod() != null) {
			jsonObject.put("gatewayAuthMethod", this.getGatewayAuthMethod().name());
		}
		if (this.getGatewayType() != null) {
			jsonObject.put("gatewayType", this.getGatewayType().name());
		}
		if (this.getLastAccessedGatewayId() != null) {
			jsonObject.put("gatewayAuthMethod", this.getLastAccessedGatewayId());
		}
		if (this.getLastAccessedGatewayTime() != null) {
			jsonObject.put("lastAccessedGatewayTime", this.getLastAccessedGatewayTime());
		}
		return jsonObject;
	}

}

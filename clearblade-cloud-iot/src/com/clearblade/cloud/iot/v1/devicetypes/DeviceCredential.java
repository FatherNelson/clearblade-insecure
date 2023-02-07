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

import com.clearblade.cloud.iot.v1.registrytypes.PublicKeyCredential;

public class DeviceCredential {

	private String expirationTime = "";
	private PublicKeyCredential publicKey;

	public DeviceCredential() {
	}

	private DeviceCredential(Builder builder) {
		expirationTime = builder.expirationTime;
		publicKey = builder.publicKey;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private String expirationTime;
		private PublicKeyCredential publicKey;

		protected Builder() {

		}

		public String getExpirationTime() {
			return expirationTime;
		}

		public Builder setExpirationTime(String expirationTime) {
			this.expirationTime = expirationTime;
			return this;
		}

		public PublicKeyCredential getPublicKey() {
			return publicKey;
		}

		public Builder setPublicKey(PublicKeyCredential publicKey) {
			this.publicKey = publicKey;
			return this;
		}

		private Builder(DeviceCredential deviceCredential) {
			this.expirationTime = deviceCredential.expirationTime;
			this.publicKey = deviceCredential.publicKey;
		}

		public DeviceCredential build() {
			return new DeviceCredential(this);
		}
	}

	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

	public void setPublicKey(PublicKeyCredential publicKey) {
		this.publicKey = publicKey;
	}

	public boolean isEmpty() {
		return publicKey == null;
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("expirationTime", this.expirationTime);
		jsonObject.put("publicKey", this.publicKey.toJSONObject());
		return jsonObject;
	}
}

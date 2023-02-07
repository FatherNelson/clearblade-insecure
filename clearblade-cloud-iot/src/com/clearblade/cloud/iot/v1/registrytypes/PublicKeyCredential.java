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

package com.clearblade.cloud.iot.v1.registrytypes;

import org.json.simple.JSONObject;

public class PublicKeyCredential {

	private PublicKeyFormat format;
	private String key;

	public PublicKeyCredential() {
	}

	public void setFormat(PublicKeyFormat format) {
		this.format = format;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private PublicKeyCredential(Builder builder) {
		format = builder.format;
		key = builder.key;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private PublicKeyFormat format;
		private String key;

		protected Builder() {

		}

		public PublicKeyFormat getFormat() {
			return format;
		}

		public Builder setFormat(PublicKeyFormat format) {
			this.format = format;
			return this;
		}

		public String getKey() {
			return key;
		}

		public Builder setKey(String key) {
			this.key = key;
			return this;
		}

		private Builder(PublicKeyCredential publicKeyCredential) {
			this.format = publicKeyCredential.format;
			this.key = publicKeyCredential.key;
		}

		public PublicKeyCredential build() {
			return new PublicKeyCredential(this);
		}
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("format", this.format.name());
		jsonObject.put("key", this.key);
		return jsonObject;
	}

}

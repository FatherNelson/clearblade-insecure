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

public class HttpConfig {

	private HttpState httpEnabledState;

	public HttpConfig() {
		httpEnabledState = HttpState.HTTP_ENABLED;
	}

	private HttpConfig(Builder builder) {
		this.httpEnabledState = builder.getHttpState();
	}

	public HttpState getHttpEnabledState() {
		return httpEnabledState;
	}

	public void setHttpEnabledState(HttpState httpEnabledState) {
		this.httpEnabledState = httpEnabledState;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	/**
	 * Builder for setting
	 * HttpConfig - httpConfig
	 */
	public static class Builder {
		private HttpState httpState;

		protected Builder() {

		}

		private Builder(HttpConfig config) {
			this.httpState = config.httpEnabledState;
		}

		public HttpConfig build() {
			return new HttpConfig(this);
		}

		public HttpState getHttpState() {
			return httpState;
		}

		public Builder setHttpState(HttpState state) {
			this.httpState = state;
			return this;
		}

	}

	@Override
	public String toString() {
		return "{'httpEnabledState':'" + this.httpEnabledState + "'}";
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJsonObject() {
		JSONObject json = new JSONObject();
		json.put("httpEnabledState", this.httpEnabledState.toString());
		return json;
	}
}

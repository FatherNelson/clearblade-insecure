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

public class FieldMask {

	private final String name;

	public FieldMask() {
		name = "";
	}

	private FieldMask(Builder builder) {
		name = builder.getFieldmask();
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private String name;

		protected Builder() {

		}

		public String getFieldmask() {
			return name;
		}

		public Builder setFieldmask(String fieldmask) {
			this.name = fieldmask;
			return this;
		}

		private Builder(FieldMask fieldMask) {
			this.name = fieldMask.name;
		}

		public FieldMask build() {
			return new FieldMask(this);
		}
	}

	@Override
	public String toString() {
		if (this.name != null)
			return this.name;
		else
			return "";
	}
}

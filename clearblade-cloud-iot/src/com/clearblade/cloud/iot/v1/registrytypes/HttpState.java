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

public enum HttpState {
	HTTP_STATE_UNSPECIFIED(0),
	HTTP_ENABLED(1),
	HTTP_DISABLED(2),
	UNRECOGNIZED(-1),
	;

	public static final int HTTP_STATE_UNSPECIFIED_VALUE = 0;
	public static final int HTTP_ENABLED_VALUE = 1;
	public static final int HTTP_DISABLED_VALUE = 2;

	public final int getNumber() {
		if (this == UNRECOGNIZED) {
			throw new java.lang.IllegalArgumentException(
					"Can't get the number of an unknown enum value.");
		}
		return value;
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static HttpState valueOf(int value) {
		return forNumber(value);
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static HttpState forNumber(int value) {
		switch (value) {
			case 0:
				return HTTP_STATE_UNSPECIFIED;
			case 1:
				return HTTP_ENABLED;
			case 2:
				return HTTP_DISABLED;
			default:
				return null;
		}
	}

	public HttpState findValueByNumber(int number) {
		return HttpState.forNumber(number);
	}

	private final int value;

	private HttpState(int value) {
		this.value = value;
	}

}

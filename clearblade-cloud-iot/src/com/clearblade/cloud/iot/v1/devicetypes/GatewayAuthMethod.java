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

public enum GatewayAuthMethod {

	GATEWAY_AUTH_METHOD_UNSPECIFIED(0),
	ASSOCIATION_ONLY(1),
	DEVICE_AUTH_TOKEN_ONLY(2),
	ASSOCIATION_AND_DEVICE_AUTH_TOKEN(3),
	UNRECOGNIZED(-1),
	;

	public static final int GATEWAY_AUTH_METHOD_UNSPECIFIED_VALUE = 0;
	public static final int ASSOCIATION_ONLY_VALUE = 1;
	public static final int DEVICE_AUTH_TOKEN_ONLY_VALUE = 2;
	public static final int ASSOCIATION_AND_DEVICE_AUTH_TOKEN_VALUE = 3;

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
	public static GatewayAuthMethod valueOf(int value) {
		return forNumber(value);
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static GatewayAuthMethod forNumber(int value) {
		switch (value) {
			case 0:
				return GATEWAY_AUTH_METHOD_UNSPECIFIED;
			case 1:
				return ASSOCIATION_ONLY;
			case 2:
				return DEVICE_AUTH_TOKEN_ONLY;
			case 3:
				return ASSOCIATION_AND_DEVICE_AUTH_TOKEN;
			default:
				return null;
		}
	}

	public GatewayAuthMethod findValueByNumber(int number) {
		return GatewayAuthMethod.forNumber(number);
	}

	private final int value;

	private GatewayAuthMethod(int value) {
		this.value = value;
	}

}

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

public enum PublicKeyFormat {
	UNSPECIFIED_PUBLIC_KEY_FORMAT(0), RSA_PEM(3), RSA_X509_PEM(1), ES256_PEM(2), ES256_X509_PEM(4), UNRECOGNIZED(-1),;

	public static final int UNSPECIFIED_PUBLIC_KEY_FORMAT_VALUE = 0;
	public static final int RSA_PEM_VALUE = 3;
	public static final int RSA_X509_PEM_VALUE = 1;
	public static final int ES256_PEM_VALUE = 2;
	public static final int ES256_X509_PEM_VALUE = 4;

	public final int getNumber() {
		if (this == UNRECOGNIZED) {
			throw new java.lang.IllegalArgumentException("Can't get the number of an unknown enum value.");
		}
		return value;
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static PublicKeyFormat valueOf(int value) {
		return forNumber(value);
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static PublicKeyFormat forNumber(int value) {
		switch (value) {
			case 0:
				return UNSPECIFIED_PUBLIC_KEY_FORMAT;
			case 3:
				return RSA_PEM;
			case 1:
				return RSA_X509_PEM;
			case 2:
				return ES256_PEM;
			case 4:
				return ES256_X509_PEM;
			default:
				return null;
		}
	}

	public PublicKeyFormat findValueByNumber(int number) {
		return PublicKeyFormat.forNumber(number);
	}

	private final int value;

	private PublicKeyFormat(int value) {
		this.value = value;
	}

}

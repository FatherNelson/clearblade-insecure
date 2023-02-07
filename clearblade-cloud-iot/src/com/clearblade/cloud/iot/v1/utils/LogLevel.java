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

package com.clearblade.cloud.iot.v1.utils;

public enum LogLevel {
	LOG_LEVEL_UNSPECIFIED(0), NONE(10), ERROR(20), INFO(30), DEBUG(40), UNRECOGNIZED(-1),;

	public static final int LOG_LEVEL_UNSPECIFIED_VALUE = 0;
	public static final int NONE_VALUE = 10;
	public static final int ERROR_VALUE = 20;
	public static final int INFO_VALUE = 30;
	public static final int DEBUG_VALUE = 40;

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
	public static LogLevel valueOf(int value) {
		return forNumber(value);
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static LogLevel forNumber(int value) {
		switch (value) {
		case 0:
			return LOG_LEVEL_UNSPECIFIED;
		case 10:
			return NONE;
		case 20:
			return ERROR;
		case 30:
			return INFO;
		case 40:
			return DEBUG;
		default:
			return null;
		}
	}

	public LogLevel findValueByNumber(int number) {
		return LogLevel.forNumber(number);
	}

	private final int value;

	private LogLevel(int value) {
		this.value = value;
	}

}

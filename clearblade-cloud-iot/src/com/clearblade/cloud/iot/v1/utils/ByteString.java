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

public class ByteString {

	private String binaryDataStr;
	private ByteString binaryData;
	private byte[] binaryDataArray;
	public static final byte[] EMPTY = new byte[0];

	public ByteString(String str) {	
		this.setBinaryDataArray(str.getBytes());
	}

	public ByteString getBinaryData() {
		return binaryData;
	}

	public void setBinaryData(ByteString binaryData) {
		this.binaryData = binaryData;
	}

	public byte[] getBinaryDataArray() {
		return binaryDataArray;
	}

	public void setBinaryDataArray(byte[] binaryDataArray) {
		this.binaryDataArray = binaryDataArray;
	}

	public String getBinaryDataStr() {
		return binaryDataStr;
	}

	public void setBinaryDataStr(String binaryDataStr) {
		this.binaryDataStr = binaryDataStr;
	}
	
	

}

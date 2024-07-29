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

public class Constants {

	private Constants() {
	}

	// keys used in project
	public static final String AUTH_ACCESS = "CLEARBLADE_CONFIGURATION";
	public static final String AUTH_REGISTRY = "CLEARBLADE_REGISTRY";
	public static final String AUTH_REGION = "CLEARBLADE_REGION";
	public static final String BINARYDATA = "BINARYDATA_AND_TIME_GOOGLE_FORMAT";

	public static final String REGISTRY_URL = "REGISTRY_URL";
	public static final String REGISTRY_SYSKEY = "REGISTRY_SYSKEY";
	public static final String REGISTRY_TOKEN = "REGISTRY_TOKEN";
	public static final String ADMIN_SYSTEM_KEY = "systemKey";
	public static final String ADMIN_TOKEN = "token";
	public static final String BASE_URL = "url";
	public static final String PROJECT_NAME = "project";
	public static final String USER_SYSTEM_KEY = "systemKey";
	public static final String USER_TOKEN = "serviceAccountToken";
	public static final String API_BASE_URL = "url";

	
	// Constants used for setting up http connection - for common use
	public static final String HTTPS_URL_PREFIX = "https://";
	public static final String HTTP_REQUEST_METHOD_TYPE_POST = "POST";
	public static final String HTTP_REQUEST_METHOD_TYPE_DELETE = "DELETE";
	public static final String HTTP_REQUEST_METHOD_TYPE_GET = "GET";
	public static final String HTTP_REQUEST_METHOD_TYPE_PATCH = "PATCH";
	public static final String HTTP_REQUEST_PROPERTY_TOKEN_KEY = "ClearBlade-UserToken";
	public static final String HTTP_REQUEST_PROPERTY_CONTENT_TYPE_KEY = "Content-Type";
	public static final String HTTP_REQUEST_PROPERTY_ACCEPT_KEY = "Accept";
	public static final String HTTP_REQUEST_PROPERTY_CONTENT_TYPE_ACCEPT_VALUE = "application/json";

	// Constant values used in project
	public static final String UTF8 = "utf-8";
	public static final String ENDPOINTPORT = ":443";
	public static final String WEBHOOK = "/api/v/4/webhook/execute/";
	public static final String DEVICES_URL_EXTENSION = "/cloudiot_devices";
	public static final String CLOUDIOT_URL_EXTENSION = "/cloudiot";
	public static final String DEVICES_STATES_URL_EXTENSION = "/cloudiot_devices_states";
	public static final String CLOUDIOT_DEVICES_URL_EXTENSION = "/cloudiotdevice_devices";
	public static final String CLOUDIOT_DEVICE_CONFIG_URL_EXTENSION = "/cloudiot_devices_configVersions";
	public static final String GET_SYSTEM_CREDENTIALS_EXTENSION = "/api/v/1/code/";
}

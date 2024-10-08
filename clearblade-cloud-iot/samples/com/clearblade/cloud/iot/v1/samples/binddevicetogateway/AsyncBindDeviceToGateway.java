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

package com.clearblade.cloud.iot.v1.samples.binddevicetogateway;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayRequest;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayResponse;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class AsyncBindDeviceToGateway {
	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String GATEWAY = "";
	public static String DEVICE = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();

	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		GATEWAY = System.getProperty("gatewayName");
		DEVICE = System.getProperty("deviceName");
		if(REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		} 
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncBindDeviceToGateway();
	}

	public static void asyncBindDeviceToGateway() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		BindDeviceToGatewayRequest request = BindDeviceToGatewayRequest.Builder.newBuilder()
				.setParent(RegistryName.of(PROJECT, LOCATION, REGISTRY).toString())
				.setGateway(GATEWAY).setDevice(DEVICE).build();
		BindDeviceToGatewayResponse response = deviceManagerAsyncClient.bindDeviceToGateway(request);
		if(response != null) {
			System.out.println("BindDeviceToGateway execution successful");
		}else {
			System.out.println("BindDeviceToGateway execution failed");
		}
	}
}

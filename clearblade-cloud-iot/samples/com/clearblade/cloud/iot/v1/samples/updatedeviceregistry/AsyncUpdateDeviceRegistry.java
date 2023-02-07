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

package com.clearblade.cloud.iot.v1.samples.updatedeviceregistry;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.updatedeviceregistry.UpdateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class AsyncUpdateDeviceRegistry {
	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String LOGLEVEL = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();

	public static void main(String[] args) throws Exception {
        PROJECT = System.getProperty("projectName");
        LOCATION = System.getProperty("location");
        REGISTRY = System.getProperty("registryName");
        LOGLEVEL = System.getProperty("logLevel");
		if (REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		}
		if (LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncUpdateDeviceRegistry();
	}

	public static void asyncUpdateDeviceRegistry() throws Exception {
		DeviceManagerAsyncClient deviceManagerClient = new DeviceManagerAsyncClient();
		RegistryName name = RegistryName.of(PROJECT, LOCATION, REGISTRY);
		UpdateDeviceRegistryRequest request = UpdateDeviceRegistryRequest.Builder.newBuilder()
				.setDeviceRegistry(DeviceRegistry.newBuilder().setId(REGISTRY).setName(name.getRegistryFullName())
						.setLogLevel(LogLevel.valueOf(LOGLEVEL)).build())
				.setName(name.getRegistryFullName()).setUpdateMask("logLevel").build();
		try {
			DeviceRegistry response = deviceManagerClient.updateDeviceRegistry(request);
			if (response != null) {
				System.out.println(response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}

}

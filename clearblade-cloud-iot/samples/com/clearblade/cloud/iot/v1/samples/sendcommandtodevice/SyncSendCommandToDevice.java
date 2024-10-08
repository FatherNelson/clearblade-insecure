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

package com.clearblade.cloud.iot.v1.samples.sendcommandtodevice;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceRequest;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceResponse;
import com.clearblade.cloud.iot.v1.utils.ByteString;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class SyncSendCommandToDevice {

	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	public static String BINARYDATA = "";
	public static String SUBFOLDER = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();
	
	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		BINARYDATA = System.getProperty("binaryData");
		SUBFOLDER = System.getProperty("subFolder");
		if(REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		} 
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		syncSendCommandToDevice();
	}

	public static void syncSendCommandToDevice() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		SendCommandToDeviceRequest request = SendCommandToDeviceRequest.Builder.newBuilder()
				.setName(DeviceName.of(PROJECT, LOCATION, REGISTRY, DEVICE).toString())
				.setBinaryData(new ByteString(BINARYDATA)).setSubfolder(SUBFOLDER).build();
		SendCommandToDeviceResponse response = deviceManagerClient.sendCommandToDevice(request);
		if(response != null) {
			System.out.println("SendCommandToDevice execution successful");
		}else {
			System.out.println("SendCommandToDevice execution failed");
		}
	}
}

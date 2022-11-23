package com.clearblade.cloud.iot.v1.samples.updatedeviceregistry;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.updatedeviceregistry.UpdateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class AsyncUpdateDeviceRegistry {
	public static void main(String[] args) throws Exception {
		asyncUpdateDeviceRegistry();
	}

	public static void asyncUpdateDeviceRegistry() throws Exception {
		DeviceManagerAsyncClient deviceManagerClient = new DeviceManagerAsyncClient();
		RegistryName name = RegistryName.of("ingressdevelopmentenv", "us-central1", "Registry_Test1");
		UpdateDeviceRegistryRequest request = UpdateDeviceRegistryRequest.Builder.newBuilder()
				.setDeviceRegistry(DeviceRegistry.newBuilder().setId("Rashmi_Registry_Test")
						.setName(name.getRegistryFullName())
						.setLogLevel(LogLevel.DEBUG)
						.build())
				.setName(name.getRegistryFullName()).setUpdateMask("logLevel").build();

		DeviceRegistry response = deviceManagerClient.updateDeviceRegistry(request);
		if (response != null) {
			System.out.println(response.toBuilder().getName() + "::" + response.toBuilder().getLogLevel());
		}
	}

}

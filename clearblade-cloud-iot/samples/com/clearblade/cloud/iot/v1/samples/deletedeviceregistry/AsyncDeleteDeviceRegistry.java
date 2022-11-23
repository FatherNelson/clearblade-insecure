package com.clearblade.cloud.iot.v1.samples.deletedeviceregistry;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.deletedeviceregistry.DeleteDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class AsyncDeleteDeviceRegistry {

	public static void main(String[] args) throws Exception {
		asyncDeleteDeviceRegistry();
	}

	public static void asyncDeleteDeviceRegistry() throws Exception {

		DeviceManagerAsyncClient deviceManagerClient = new DeviceManagerAsyncClient();
		DeleteDeviceRegistryRequest request = DeleteDeviceRegistryRequest.Builder.newBuilder()
				.setName(RegistryName.of("ingressdevelopmentenv", "us-central1", "testCreate_reg2")
						.getRegistryFullName())
				.build();
		deviceManagerClient.deleteDeviceRegistry(request);

	}

}
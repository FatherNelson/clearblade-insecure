package com.clearblade.cloud.iot.v1.samples.deletedeviceregistry;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.deletedeviceregistry.DeleteDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class SyncDeleteDeviceRegistry {

	public static void main(String[] args) throws Exception {
		syncDeleteDeviceRegistry();
	}

	public static void syncDeleteDeviceRegistry() throws Exception {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		DeleteDeviceRegistryRequest request = DeleteDeviceRegistryRequest.Builder.newBuilder()
				.setName(RegistryName.of("ingressdevelopmentenv", "us-central1", "testCreate_reg3")
						.getRegistryFullName())
				.build();
		deviceManagerClient.deleteDeviceRegistry(request);
	}

}

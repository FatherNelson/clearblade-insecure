package com.clearblade.cloud.iot.v1.samples.deletedeviceregistry;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.deletedeviceregistry.DeleteDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class SyncDeleteDeviceRegistry {

	public static String PROJECT = "";
	public static String  LOCATION = "";
	public static String  REGISTRY = "";

	public static void main(String[] args) throws Exception {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		syncDeleteDeviceRegistry();
	}

	public static void syncDeleteDeviceRegistry() throws Exception {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		DeleteDeviceRegistryRequest request = DeleteDeviceRegistryRequest.Builder.newBuilder()
				.setName(RegistryName.of(PROJECT,LOCATION,REGISTRY)
						.getRegistryFullName())
				.build();
		deviceManagerClient.deleteDeviceRegistry(request);
	}

}

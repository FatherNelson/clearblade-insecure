package com.clearblade.cloud.iot.v1.samples.deletedeviceregistry;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.deletedeviceregistry.DeleteDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class AsyncDeleteDeviceRegistry {

	public static String PROJECT = "";
	public static String  LOCATION = "";
	public static String  REGISTRY = "";

	public static void main(String[] args) throws Exception {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		asyncDeleteDeviceRegistry();
	}

	public static void asyncDeleteDeviceRegistry() throws Exception {
	
		DeviceManagerAsyncClient deviceManagerClient = new DeviceManagerAsyncClient();
		DeleteDeviceRegistryRequest request = DeleteDeviceRegistryRequest.Builder.newBuilder()
				.setName(RegistryName.of(PROJECT, LOCATION, REGISTRY)
						.getRegistryFullName())
				.build();
		deviceManagerClient.deleteDeviceRegistry(request);
		
	}

}
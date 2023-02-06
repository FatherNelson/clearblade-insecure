package com.clearblade.cloud.iot.v1.samples.deletedeviceregistry;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.deletedeviceregistry.DeleteDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class AsyncDeleteDeviceRegistry {

	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();

	public static void main(String[] args) throws Exception {
        PROJECT = System.getProperty("projectName");
        LOCATION = System.getProperty("location");
        REGISTRY = System.getProperty("registryName");
		if (REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		}
		if (LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncDeleteDeviceRegistry();
	}

	public static void asyncDeleteDeviceRegistry() throws Exception {

		DeviceManagerAsyncClient deviceManagerClient = new DeviceManagerAsyncClient();
		DeleteDeviceRegistryRequest request = DeleteDeviceRegistryRequest.Builder.newBuilder()
				.setName(RegistryName.of(PROJECT, LOCATION, REGISTRY).getRegistryFullName()).build();
		try {
			deviceManagerClient.deleteDeviceRegistry(request);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}

}
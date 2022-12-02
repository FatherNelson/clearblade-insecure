package com.clearblade.cloud.iot.v1.samples.createdeviceregistry;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.createdeviceregistry.CreateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.registrytypes.LocationName;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class SyncCreateDeviceRegistry {

	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();
	
	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		if(REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		} 
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
    syncCreateDeviceRegistry();
    }

    public static void syncCreateDeviceRegistry() {
        DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
        CreateDeviceRegistryRequest request = CreateDeviceRegistryRequest.Builder.newBuilder()
                .setParent(LocationName.of(PROJECT, LOCATION).toString())
                .setDeviceRegistry(
                        DeviceRegistry.newBuilder().setId(REGISTRY).build())
                .build();
        DeviceRegistry response = deviceManagerClient.createDeviceRegistry(request);
		if(response != null) {
			System.out.println("CreateDeviceRegistry execution successful");
		}else {
			System.out.println("CreateDeviceRegistry execution failed");
		}

    }
}

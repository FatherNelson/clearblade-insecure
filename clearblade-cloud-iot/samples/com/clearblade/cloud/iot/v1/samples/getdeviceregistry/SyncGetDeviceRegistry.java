package com.clearblade.cloud.iot.v1.samples.getdeviceregistry;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.getdeviceregistry.GetDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class SyncGetDeviceRegistry {
	static Logger log = Logger.getLogger(SyncGetDeviceRegistry.class.getName());
	public static String PROJECT = "";
	public static String  LOCATION = "";
	public static String  REGISTRY = "";
	
	public static void main(String[] args) {		
		PROJECT = args[0];
		LOCATION = args[1];
		REGISTRY = args[2];
		syncGetDeviceRegistry();
	}

	public static void syncGetDeviceRegistry() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		GetDeviceRegistryRequest request = GetDeviceRegistryRequest.Builder.newBuilder()
				.setName(RegistryName.of(PROJECT, LOCATION, REGISTRY).getRegistryFullName())
				.build();
		DeviceRegistry response = deviceManagerAsyncClient.getDeviceRegistry(request);
		System.out.println(response.createDeviceJSONObject(""));
	}

}

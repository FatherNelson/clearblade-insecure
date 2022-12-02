package com.clearblade.cloud.iot.v1.samples.listdeviceregistries;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.listdeviceregistries.ListDeviceRegistriesRequest;
import com.clearblade.cloud.iot.v1.listdeviceregistries.ListDeviceRegistriesResponse;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.registrytypes.LocationName;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class AsyncListDeviceRegistries {
	public static String PROJECT = "";
	public static String LOCATION = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();
	
	public static void main(String[] args) throws Exception{		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncListDeviceRegistries();
	}

	public static void asyncListDeviceRegistries() throws Exception {
		DeviceManagerAsyncClient deviceManagerClient = new DeviceManagerAsyncClient();
		ListDeviceRegistriesRequest request = ListDeviceRegistriesRequest.Builder.newBuilder()
				.setParent(LocationName.of(PROJECT, LOCATION).getLocationFullName())
				.build();
		ListDeviceRegistriesResponse response = deviceManagerClient.listDeviceRegistries(request);
		if(response != null) {
			System.out.println("DeviceRegistriesList fetch successful");
			for (DeviceRegistry element : response.getDeviceRegistriesList()) {
				System.out.println(element.toBuilder().getName());
			}
			System.out.println(response.getNextPageToken());

		}else {
			System.out.println("DeviceRegistriesList fetch failed");			
		}

	}

}

package com.clearblade.cloud.iot.v1.samples.listdeviceregistries;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.listdeviceregistries.ListDeviceRegistriesRequest;
import com.clearblade.cloud.iot.v1.listdeviceregistries.ListDeviceRegistriesResponse;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.registrytypes.LocationName;

public class AsyncListDeviceRegistries {
	public static void main(String[] args) throws Exception {
		asyncListDeviceRegistries();
	}

	public static void asyncListDeviceRegistries() throws Exception {
		DeviceManagerAsyncClient deviceManagerClient = new DeviceManagerAsyncClient();
		ListDeviceRegistriesRequest request = ListDeviceRegistriesRequest.Builder.newBuilder()
				.setParent(LocationName.of("ingressdevelopmentenv", "us-central1").getLocationFullName())
				.build();
		ListDeviceRegistriesResponse response = deviceManagerClient.listDeviceRegistries(request);

		for (DeviceRegistry element : response.getDeviceRegistriesList()) {
			System.out.println(element.toBuilder().getName());

		}
		System.out.println(response.getNextPageToken());
	}

}

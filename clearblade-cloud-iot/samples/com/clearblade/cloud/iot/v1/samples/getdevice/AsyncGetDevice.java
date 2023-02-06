package com.clearblade.cloud.iot.v1.samples.getdevice;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.devicetypes.FieldMask;
import com.clearblade.cloud.iot.v1.getdevice.GetDeviceRequest;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class AsyncGetDevice {
	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();

	public static void main(String[] args) {
        PROJECT = System.getProperty("projectName");
        LOCATION = System.getProperty("location");
        REGISTRY = System.getProperty("registryName");
        DEVICE = System.getProperty("deviceName");
		if (REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		}
		if (LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncGetDevice();
	}

	public static void asyncGetDevice() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		DeviceName name = DeviceName.of(PROJECT, LOCATION, REGISTRY, DEVICE);
		GetDeviceRequest request = GetDeviceRequest.Builder.newBuilder().setName(name)
				.setFieldMask(FieldMask.newBuilder().build()).build();
		try {
			Device response = deviceManagerAsyncClient.getDevice(request);
			System.out.print(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

package com.clearblade.cloud.iot.v1.samples.getdevice;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.devicetypes.FieldMask;
import com.clearblade.cloud.iot.v1.getdevice.GetDeviceRequest;

public class AsyncGetDevice {
	static Logger log = Logger.getLogger(AsyncGetDevice.class.getName());
	public static String PROJECT = "";
	public static String  LOCATION = "";
	public static String  REGISTRY = "";
	public static String  DEVICE = "";

	public static void main(String[] args) {		
		PROJECT = args[0];
		LOCATION = args[1];
		REGISTRY = args[2];
		DEVICE = args[3];
		asyncGetDevice();
	}

	public static void asyncGetDevice() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		DeviceName name = DeviceName.of(PROJECT, LOCATION, REGISTRY,DEVICE);
		GetDeviceRequest request = GetDeviceRequest.Builder.newBuilder().setName(name)
				.setFieldMask(FieldMask.newBuilder().build()).build();
		Device response = deviceManagerAsyncClient.getDevice(request);
		System.out.println(response.toBuilder().getName());
	}

}

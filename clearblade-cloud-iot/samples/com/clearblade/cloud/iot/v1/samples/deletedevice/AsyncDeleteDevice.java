package com.clearblade.cloud.iot.v1.samples.deletedevice;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.deletedevice.DeleteDeviceRequest;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;

public class AsyncDeleteDevice {
	static Logger log = Logger.getLogger(AsyncDeleteDevice.class.getName());

	public static void main(String[] args) {
		asyncDeleteDevice();
	}

	public static void asyncDeleteDevice() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		DeviceName deviceName = DeviceName.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test",
				"AsyncTest22");
		DeleteDeviceRequest request = DeleteDeviceRequest.Builder.newBuilder().setName(deviceName).build();
		deviceManagerAsyncClient.deleteDevice(request);
	}

}

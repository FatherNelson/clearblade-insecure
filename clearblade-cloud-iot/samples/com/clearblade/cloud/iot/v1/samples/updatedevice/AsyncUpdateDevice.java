package com.clearblade.cloud.iot.v1.samples.updatedevice;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.updatedevice.UpdateDeviceRequest;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class AsyncUpdateDevice {
	static Logger log = Logger.getLogger(AsyncUpdateDevice.class.getName());

	public static void main(String[] args) {
		asyncUpdateDevice();
	}

	public static void asyncUpdateDevice() {
		DeviceManagerAsyncClient deviceManagerAsyncClient = new DeviceManagerAsyncClient();
		Device device = Device.patch("myOldDevice", "myOldDevice", LogLevel.ERROR, true);
		String updateMask = "logLevel";

		UpdateDeviceRequest request = UpdateDeviceRequest.Builder.newBuilder().setName("myOldDevice").setDevice(device)
				.setUpdateMask(updateMask).build();
		Device response = deviceManagerAsyncClient.updateDevice(request);
		System.out.println(response.toBuilder().getName() + "::" + response.toBuilder().getLogLevel().name());

	}
}

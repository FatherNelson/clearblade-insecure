package com.clearblade.cloud.iot.v1.samples.updatedevice;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.updatedevice.UpdateDeviceRequest;
import com.clearblade.cloud.iot.v1.utils.LogLevel;

public class SyncUpdateDevice {
	static Logger log = Logger.getLogger(SyncUpdateDevice.class.getName());

	public static void main(String[] args) {
		syncUpdateDevice();
	}

	public static void syncUpdateDevice() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();

		Device device = Device.patch("myOldDevice", "myOldDevice", LogLevel.DEBUG, true);
		String updateMask = "logLevel";

		UpdateDeviceRequest request = UpdateDeviceRequest.Builder.newBuilder().setName("myOldDevice").setDevice(device)
				.setUpdateMask(updateMask).build();
		Device response = deviceManagerClient.updateDevice(request);
		System.out.println(response.toBuilder().getName() + "::" + response.toBuilder().getLogLevel().name());
	}
}

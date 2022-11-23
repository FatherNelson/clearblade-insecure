package com.clearblade.cloud.iot.v1.samples.deletedevice;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.deletedevice.DeleteDeviceRequest;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;

public class SyncDeleteDevice {
	static Logger log = Logger.getLogger(SyncDeleteDevice.class.getName());

	public static void main(String[] args) {
		syncDeleteDevice();
	}

	public static void syncDeleteDevice() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		DeviceName deviceName = DeviceName.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test",
				"SyncTest22");
		DeleteDeviceRequest request = DeleteDeviceRequest.Builder.newBuilder().setName(deviceName).build();
		deviceManagerClient.deleteDevice(request);
	}

}

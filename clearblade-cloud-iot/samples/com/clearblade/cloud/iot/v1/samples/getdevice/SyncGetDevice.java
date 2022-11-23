package com.clearblade.cloud.iot.v1.samples.getdevice;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.devicetypes.FieldMask;
import com.clearblade.cloud.iot.v1.getdevice.GetDeviceRequest;

public class SyncGetDevice {
	static Logger log = Logger.getLogger(SyncGetDevice.class.getName());

	public static void main(String[] args) {
		syncGetDevice();
	}

	public static void syncGetDevice() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		DeviceName name = DeviceName.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test",
				"Rashmi_Device_Test");
		GetDeviceRequest request = GetDeviceRequest.Builder.newBuilder().setName(name)
				.setFieldMask(FieldMask.newBuilder().build()).build();
		Device response = deviceManagerClient.getDevice(request);
		System.out.println(response.toBuilder().getName());
	}

}

package com.clearblade.cloud.iot.v1.getdevice;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.utils.Device;
import com.clearblade.cloud.iot.v1.utils.DeviceName;
import com.clearblade.cloud.iot.v1.utils.FieldMask;

public class SyncGetDevice {
	static Logger log = Logger.getLogger(SyncGetDevice.class.getName());

	public static void main(String[] args) {
		syncGetDevice();
	}

	public static void syncGetDevice() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		DeviceName name = DeviceName.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test", "Rashmi_Device_Test");
		GetDeviceRequest request = GetDeviceRequest.Builder.newBuilder().setName(name).setFieldMask(FieldMask.newBuilder().build()).build();
		Device response = deviceManagerClient.getDevice(request);
		System.out.println(response.toBuilder().getName());
	}


}

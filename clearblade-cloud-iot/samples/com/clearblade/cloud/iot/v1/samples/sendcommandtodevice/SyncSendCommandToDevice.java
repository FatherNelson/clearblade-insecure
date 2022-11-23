package com.clearblade.cloud.iot.v1.samples.sendcommandtodevice;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceRequest;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceResponse;
import com.clearblade.cloud.iot.v1.utils.ByteString;

public class SyncSendCommandToDevice {

	static Logger log = Logger.getLogger(SyncSendCommandToDevice.class.getName());

	public static void main(String[] args) {
		syncSendCommandToDevice();
	}

	public static void syncSendCommandToDevice() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		SendCommandToDeviceRequest request = SendCommandToDeviceRequest.Builder.newBuilder()
				.setName(DeviceName
						.of("ingressdevelopmentenv", "us-central1", "MandarTest1", "mandar_device")
						.toString())
				.setBinaryData(new ByteString("c2VuZEZ1bm55TWVzc2FnZVRvRGV2aWNl")).setSubfolder("testdata").build();
		SendCommandToDeviceResponse response = deviceManagerClient.sendCommandToDevice(request);
		System.out.println(response.toString());
	}
}

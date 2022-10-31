package com.clearblade.cloud.iot.v1;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.utils.ByteString;
import com.clearblade.cloud.iot.v1.utils.Constants;
import com.clearblade.cloud.iot.v1.utils.DeviceName;

public class AsyncSendCommandToDevice {
	
	static Logger log = Logger.getLogger(AsyncSendCommandToDevice.class.getName());
	
	public static void main(String[] args) {
		asyncSendCommandToDevice();
	}

	public static void asyncSendCommandToDevice() {
		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		Constants constants = new Constants();
		String msg = "";
		DeviceName name = DeviceName.of(constants.getProject(), constants.getLocation(),
				constants.getRegistry(), constants.getDeviceName());
		SendCommandToDeviceRequest request = SendCommandToDeviceRequest.Builder.newBuilder().setName(name)
				.setBinaryDataByte(ByteString.EMPTY).setSubFolder("subfolder153561774").build();
		SendCommandToDeviceResponse response = deviceManagerClient.sendCommandToDevice(request);
		if (response != null) {
			response.processRequest();
			if(response.getHttpStatusResponse().equals("OK")) {
				msg = "AsyncSendCommandToDevice Method worked successfully :: " + response.getHttpStatusResponse();
				log.log(Level.INFO, msg);
			}else {
				msg = "AsyncSendCommandToDevice Method execution failed :: " + response.getHttpStatusResponse();
				log.log(Level.SEVERE, msg);
			}
		}else {
			msg = "AsyncSendCommandToDevice Method execution failed";
			log.log(Level.SEVERE, msg);
		}
	}
}

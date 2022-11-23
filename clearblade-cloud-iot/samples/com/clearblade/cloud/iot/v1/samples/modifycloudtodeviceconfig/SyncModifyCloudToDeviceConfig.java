package com.clearblade.cloud.iot.v1.samples.modifycloudtodeviceconfig;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.DeviceManagerClient;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.modifycloudtodeviceconfig.ModifyCloudToDeviceConfigRequest;
import com.clearblade.cloud.iot.v1.utils.ByteString;

public class SyncModifyCloudToDeviceConfig {
	static Logger log = Logger.getLogger(SyncModifyCloudToDeviceConfig.class.getName());

	public static void main(String[] args) {
		syncModifyCloudToDeviceConfig();
	}

	public static void syncModifyCloudToDeviceConfig() {

		DeviceManagerClient deviceManagerClient = new DeviceManagerClient();
		ModifyCloudToDeviceConfigRequest request = ModifyCloudToDeviceConfigRequest.Builder.newBuilder()
				.setName(DeviceName
						.of("ingressdevelopmentenv", "us-central1", "Rashmi_Registry_Test", "Rashmi_Device_Test")
						.toString())
				.setBinaryData(new ByteString("bmV3TWVzc2FnZUZvckNvbmZpZw==")).setVersionToUpdate("6").build();
		DeviceConfig response = deviceManagerClient.modifyCloudToDeviceConfig(request);
		System.out.println(response.toString());
	}
}

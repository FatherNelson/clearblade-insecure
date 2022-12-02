package com.clearblade.cloud.iot.v1.samples.modifycloudtodeviceconfig;

import com.clearblade.cloud.iot.v1.DeviceManagerAsyncClient;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.modifycloudtodeviceconfig.ModifyCloudToDeviceConfigRequest;
import com.clearblade.cloud.iot.v1.utils.ByteString;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class AsyncModifyCloudToDeviceConfig {

	public static String PROJECT = "";
	public static String LOCATION = "";
	public static String REGISTRY = "";
	public static String DEVICE = "";
	public static String BINARYDATA = "";
	public static String VERSIONTOUPDATE = "";
	static ConfigParameters configParameters = ConfigParameters.getInstance();
	
	public static void main(String[] args) {		
		PROJECT = System.getProperty("projectName");
		LOCATION = System.getProperty("location");
		REGISTRY = System.getProperty("registryName");
		DEVICE = System.getProperty("deviceName");
		BINARYDATA = System.getProperty("binaryData");
		VERSIONTOUPDATE = System.getProperty("versionToUpdate");
		if(REGISTRY != null) {
			configParameters.setRegistry(REGISTRY);
		} 
		if(LOCATION != null) {
			configParameters.setRegion(LOCATION);
		}
		asyncModifyCloudToDeviceConfig();
	}

	public static void asyncModifyCloudToDeviceConfig() {
		DeviceManagerAsyncClient deviceManagerClient = new DeviceManagerAsyncClient();
		ModifyCloudToDeviceConfigRequest request = ModifyCloudToDeviceConfigRequest.Builder.newBuilder()
				.setName(DeviceName.of(PROJECT,LOCATION, REGISTRY, DEVICE).toString())
				.setBinaryData(new ByteString(BINARYDATA)).setVersionToUpdate(VERSIONTOUPDATE).build();
		DeviceConfig response = deviceManagerClient.modifyCloudToDeviceConfig(request);
		if(response != null) {
			System.out.println("ModifyDeviceToConfig execution successful");
		}else {
			System.out.println("ModifyDeviceToConfig execution failed");
		}
	}
}

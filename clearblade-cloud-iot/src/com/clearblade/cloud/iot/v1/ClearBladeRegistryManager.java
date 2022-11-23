package com.clearblade.cloud.iot.v1;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.createdeviceregistry.CreateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.deletedeviceregistry.DeleteDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.getdeviceregistry.GetDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.updatedeviceregistry.UpdateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class ClearBladeRegistryManager {
	static Logger log = Logger.getLogger(ClearBladeRegistryManager.class.getName());
	ConfigParameters configParameters = new ConfigParameters();

	public DeviceRegistry getRegistry(GetDeviceRegistryRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] responseArray = syncClient.get(configParameters.getCloudiotURLExtension(), request.toString(), false);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				DeviceRegistry deviceRegistry = DeviceRegistry.newBuilder().build();
				deviceRegistry.loadFromString(responseArray[2]);
				return deviceRegistry;
			}
		}
		return null;
	}

	public DeviceRegistry asyncGetDeviceRegistry(GetDeviceRegistryRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] responseArray = asyncClient.asyncGetRegistry(configParameters.getCloudiotURLExtension(),
					request.toString());
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					DeviceRegistry deviceRegistry = DeviceRegistry.newBuilder().build();
					deviceRegistry.loadFromString(responseArray[2]);
					return deviceRegistry;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public DeviceRegistry createDeviceRegistry(CreateDeviceRegistryRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] bodyParams = request.getBodyAndParams();

		String[] responseArray = syncClient.post(configParameters.getCloudiotURLExtension(), bodyParams[0],
				bodyParams[1], true);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				DeviceRegistry deviceRegistry = DeviceRegistry.newBuilder().build();
				deviceRegistry.loadFromString(responseArray[2]);
				return deviceRegistry;
			}
		}
		return null;
	}

	public DeviceRegistry asyncCreateDeviceRegistry(CreateDeviceRegistryRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] bodyParams = request.getBodyAndParams();

			String[] responseArray = asyncClient.asyncCreateDeviceRegistry(configParameters.getCloudiotURLExtension(),
					bodyParams[0],
					bodyParams[1], true);
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					DeviceRegistry deviceRegistry = DeviceRegistry.newBuilder().build();
					deviceRegistry.loadFromString(responseArray[2]);
					return deviceRegistry;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public DeviceRegistry updateDeviceRegistry(UpdateDeviceRegistryRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] bodyParams = request.getBodyAndParams();

		String[] responseArray = syncClient.update(configParameters.getCloudiotURLExtension(), bodyParams[0],
				bodyParams[1]);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				DeviceRegistry deviceRegistry = DeviceRegistry.newBuilder().build();
				deviceRegistry.loadFromString(responseArray[2]);
				return deviceRegistry;
			}
		}
		return null;
	}

	public DeviceRegistry asyncUpdateDeviceRegistry(UpdateDeviceRegistryRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] bodyParams = request.getBodyAndParams();

			String[] responseArray = asyncClient.asyncUpdateDeviceRegistry(configParameters.getCloudiotURLExtension(),
					bodyParams[0],
					bodyParams[1]);
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					DeviceRegistry deviceRegistry = DeviceRegistry.newBuilder().build();
					deviceRegistry.loadFromString(responseArray[2]);
					return deviceRegistry;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public void deleteDeviceRegistry(DeleteDeviceRegistryRequest request) {
		SyncClient syncClient = new SyncClient();
		String bodyParams = request.getParams();

		String[] responseArray = syncClient.delete(configParameters.getCloudiotURLExtension(), bodyParams, true);
		if (responseArray[0] != null) {
			log.log(Level.INFO, "Response code " + responseArray[0] + " received with message" + responseArray[2]);
		}

	}

	public void asyncDeleteDeviceRegistry(DeleteDeviceRegistryRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String bodyParams = request.getParams();

			String[] responseArray = asyncClient.asyncDeleteDeviceRegistry(configParameters.getCloudiotURLExtension(),
					bodyParams,
					"", true);
			if (responseArray[0] != null) {
				log.log(Level.INFO, "Response code " + responseArray[0] + " received with message" + responseArray[2]);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}

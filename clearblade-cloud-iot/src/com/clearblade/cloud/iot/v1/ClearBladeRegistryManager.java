/*
 * Copyright 2023 ClearBlade Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.clearblade.cloud.iot.v1;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.createdeviceregistry.CreateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.deletedeviceregistry.DeleteDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.exception.ApplicationException;
import com.clearblade.cloud.iot.v1.getdeviceregistry.GetDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.updatedeviceregistry.UpdateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class ClearBladeRegistryManager {
	static Logger log = Logger.getLogger(ClearBladeRegistryManager.class.getName());
	ConfigParameters configParameters = ConfigParameters.getInstance();

	public DeviceRegistry getRegistry(GetDeviceRegistryRequest request) throws ApplicationException {
		SyncClient syncClient = new SyncClient();
		String[] responseArray = syncClient.get(configParameters.getCloudiotURLExtension(), request.toString(), false);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				DeviceRegistry deviceRegistry = DeviceRegistry.newBuilder().build();
				deviceRegistry.loadFromString(responseArray[2]);
				return deviceRegistry;
			} else {
				throw new ApplicationException(responseArray[2]);
			}
		}
		return null;
	}

	public DeviceRegistry asyncGetDeviceRegistry(GetDeviceRegistryRequest request) throws ApplicationException {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] responseArray = asyncClient.get(configParameters.getCloudiotURLExtension(), request.toString());
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					DeviceRegistry deviceRegistry = DeviceRegistry.newBuilder().build();
					deviceRegistry.loadFromString(responseArray[2]);
					return deviceRegistry;
				} else {
					throw new ApplicationException(responseArray[2]);
				}
			}
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}
		return null;
	}

	public DeviceRegistry createDeviceRegistry(CreateDeviceRegistryRequest request) throws ApplicationException {
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
			} else {
				throw new ApplicationException(responseArray[2]);
			}
		}
		return null;
	}

	public DeviceRegistry asyncCreateDeviceRegistry(CreateDeviceRegistryRequest request) throws ApplicationException {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] bodyParams = request.getBodyAndParams();

			String[] responseArray = asyncClient.asyncCreateDeviceRegistry(configParameters.getCloudiotURLExtension(),
					bodyParams[0], bodyParams[1], true);
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					DeviceRegistry deviceRegistry = DeviceRegistry.newBuilder().build();
					deviceRegistry.loadFromString(responseArray[2]);
					return deviceRegistry;
				} else {
					throw new ApplicationException(responseArray[2]);
				}
			}
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}
		return null;
	}

	public DeviceRegistry updateDeviceRegistry(UpdateDeviceRegistryRequest request) throws ApplicationException {
		try {
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
				} else {
					throw new ApplicationException(responseArray[2]);
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new ApplicationException(e.getMessage(), e);
		}
		return null;
	}

	public DeviceRegistry asyncUpdateDeviceRegistry(UpdateDeviceRegistryRequest request) throws ApplicationException {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] bodyParams = request.getBodyAndParams();

			String[] responseArray = asyncClient.update(configParameters.getCloudiotURLExtension(), bodyParams[0],
					bodyParams[1]);
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					DeviceRegistry deviceRegistry = DeviceRegistry.newBuilder().build();
					deviceRegistry.loadFromString(responseArray[2]);
					return deviceRegistry;
				} else {
					throw new ApplicationException(responseArray[2]);
				}
			}
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}
		return null;
	}

	public void deleteDeviceRegistry(DeleteDeviceRegistryRequest request) throws ApplicationException {
		SyncClient syncClient = new SyncClient();
		String bodyParams = request.getParams();

		String[] responseArray = syncClient.delete(configParameters.getCloudiotURLExtension(), bodyParams, true);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200 || responseCode == 204) {
				log.log(Level.INFO,
						() -> "Response code " + responseArray[0] + " received with message" + responseArray[2]);
			} else {
				throw new ApplicationException(responseArray[2]);
			}
		}

	}

	public void asyncDeleteDeviceRegistry(DeleteDeviceRegistryRequest request) throws ApplicationException {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String bodyParams = request.getParams();

			String[] responseArray = asyncClient.asyncDeleteDeviceRegistry(configParameters.getCloudiotURLExtension(),
					bodyParams, true);
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200 || responseCode == 204) {
					System.out.println("DeleteDeviceRegistry execution successful");					
				} else {
					throw new ApplicationException(responseArray[2]);
				}
			} else {
				System.out.println("DeleteDeviceRegistry execution failed");
			}
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}
	}
}

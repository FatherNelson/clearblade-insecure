package com.clearblade.cloud.iot.v1;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayRequest;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayResponse;
import com.clearblade.cloud.iot.v1.createdevice.CreateDeviceRequest;
import com.clearblade.cloud.iot.v1.deletedevice.DeleteDeviceRequest;
import com.clearblade.cloud.iot.v1.deviceslist.DevicesListRequest;
import com.clearblade.cloud.iot.v1.deviceslist.DevicesListResponse;
import com.clearblade.cloud.iot.v1.devicestateslist.ListDeviceStatesRequest;
import com.clearblade.cloud.iot.v1.devicestateslist.ListDeviceStatesResponse;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.getdevice.GetDeviceRequest;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsRequest;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsResponse;
import com.clearblade.cloud.iot.v1.listdeviceregistries.ListDeviceRegistriesRequest;
import com.clearblade.cloud.iot.v1.listdeviceregistries.ListDeviceRegistriesResponse;
import com.clearblade.cloud.iot.v1.modifycloudtodeviceconfig.ModifyCloudToDeviceConfigRequest;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceRequest;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceResponse;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayRequest;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayResponse;
import com.clearblade.cloud.iot.v1.updatedevice.UpdateDeviceRequest;
import com.clearblade.cloud.iot.v1.utils.ConfigParameters;

public class ClearBladeDeviceManager {
	static Logger log = Logger.getLogger(ClearBladeDeviceManager.class.getName());
	ConfigParameters configParameters = ConfigParameters.getInstance();

	public Device getDevice(GetDeviceRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] responseArray = syncClient.get(configParameters.getDevicesURLExtension(), request.toString(), false);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				Device device = Device.newBuilder().build();
				device.loadFromString(responseArray[2]);
				return device;
			}
		}
		return null;
	}

	public Device asyncGetDevice(GetDeviceRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] responseArray = asyncClient.get(configParameters.getDevicesURLExtension(),
					request.toString());
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					Device device = Device.newBuilder().build();
					device.loadFromString(responseArray[2]);
					return device;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public Device createDevice(CreateDeviceRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] params = request.getParams();
		String reqParams = params[0];
		String body = params[1];
		String[] responseArray = syncClient.post(configParameters.getDevicesURLExtension(), reqParams, body, false);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				Device deviceObj = Device.newBuilder().build();
				deviceObj.loadFromString(responseArray[2]);
				return deviceObj;
			}
		}
		return null;
	}

	public Device asyncCreateDevice(CreateDeviceRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] params = request.getParams();
			String reqParams = params[0];
			String body = params[1];
			String[] responseArray = asyncClient.post(configParameters.getDevicesURLExtension(), reqParams,
					body);
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					Device deviceObj = Device.newBuilder().build();
					deviceObj.loadFromString(responseArray[2]);
					return deviceObj;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public void deleteDevice(DeleteDeviceRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] responseArray = syncClient.delete(configParameters.getDevicesURLExtension(), request.toString(),
				false);
		if (responseArray[0] != null) {
			System.out.println("DeleteDevice execution successful");
			log.log(Level.INFO, ()-> "Response code " + responseArray[0] + " received with message" + responseArray[2]);
		}else {
			System.out.println("DeleteDevice execution failed");
		}
	}

	public void asyncDeleteDevice(DeleteDeviceRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] responseArray = asyncClient.delete(configParameters.getDevicesURLExtension(),
					request.toString());
			if(responseArray[0] != null) {
				System.out.println("DeleteDevice execution successful");
				log.log(Level.INFO, ()-> "Response code " + responseArray[0] + " received with message" + responseArray[2]);
			}else {
				System.out.println("DeleteDevice execution failed");
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	public Device updateDevice(UpdateDeviceRequest request) {
		try {
		SyncClient syncClient = new SyncClient();
		String[] params = request.getBodyAndParams();
		String reqParams = params[0];
		String body = params[1];
		String[] responseArray;
			responseArray = syncClient.update(configParameters.getDevicesURLExtension(), reqParams, body);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				Device deviceObj = Device.newBuilder().build();
				deviceObj.loadFromString(responseArray[2]);
				return deviceObj;
			}
		}
		} catch (InterruptedException e) {
			log.log(Level.SEVERE, e.getMessage());
			Thread.currentThread().interrupt();
		}
		return null;
	}

	public Device asyncUpdateDevice(UpdateDeviceRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] params = request.getBodyAndParams();
			String reqParams = params[0];
			String body = params[1];
			String[] responseArray = asyncClient.update(configParameters.getDevicesURLExtension(), reqParams,
					body);
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					Device deviceObj = Device.newBuilder().build();
					deviceObj.loadFromString(responseArray[2]);
					return deviceObj;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public BindDeviceToGatewayResponse bindDeviceToGateway(BindDeviceToGatewayRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] paramBody = request.getBodyAndParams();
		String[] responseArray = syncClient.post(configParameters.getCloudiotURLExtension(), paramBody[0],
				paramBody[1], false);
		BindDeviceToGatewayResponse response = BindDeviceToGatewayResponse.Builder.newBuilder().build();
		if (responseArray[0] != null) {
			response.setHttpStatusCode(Integer.parseInt(responseArray[0]));
			response.setHttpStatusResponse(responseArray[1]);
		}
		return response;
	}

	public BindDeviceToGatewayResponse asyncBindDeviceToGateway(BindDeviceToGatewayRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] paramBody = request.getBodyAndParams();
			String[] responseArray = asyncClient.post(configParameters.getCloudiotURLExtension(),
					paramBody[0], paramBody[1]);
			BindDeviceToGatewayResponse response = BindDeviceToGatewayResponse.Builder.newBuilder().build();
			if (responseArray[0] != null) {
				response.setHttpStatusCode(Integer.parseInt(responseArray[0]));
				response.setHttpStatusResponse(responseArray[1]);
			}
			return response;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public UnbindDeviceFromGatewayResponse unbindDeviceFromGateway(UnbindDeviceFromGatewayRequest request) {
		SyncClient syncClinet = new SyncClient();
		String[] paramBody = request.getBodyAndParams();
		String[] responseArray = syncClinet.post(configParameters.getCloudiotURLExtension(), paramBody[0],
				paramBody[1], false);
		UnbindDeviceFromGatewayResponse response = UnbindDeviceFromGatewayResponse.Builder.newBuilder().build();
		if (responseArray[0] != null) {
			response.setHttpStatusCode(Integer.parseInt(responseArray[0]));
			response.setHttpStatusResponse(responseArray[1]);
		}
		return response;
	}

	public UnbindDeviceFromGatewayResponse asyncUnbindDeviceFromGateway(UnbindDeviceFromGatewayRequest request) {
		try {
			AsyncClient asyncClinet = new AsyncClient();
			String[] paramBody = request.getBodyAndParams();
			String[] responseArray = asyncClinet.post(
					configParameters.getCloudiotURLExtension(), paramBody[0], paramBody[1]);
			UnbindDeviceFromGatewayResponse response = UnbindDeviceFromGatewayResponse.Builder.newBuilder().build();
			if (responseArray[0] != null) {
				response.setHttpStatusCode(Integer.parseInt(responseArray[0]));
				response.setHttpStatusResponse(responseArray[1]);
			}
			return response;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public SendCommandToDeviceResponse sendCommandToDevice(SendCommandToDeviceRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] paramBody = request.getBodyAndParams();
		String[] responseArray = syncClient.post(configParameters.getDevicesURLExtension(), paramBody[0],
				paramBody[1], false);
		SendCommandToDeviceResponse response = SendCommandToDeviceResponse.Builder.newBuilder().build();
		if (responseArray[0] != null) {
			response.setHttpStatusCode(Integer.parseInt(responseArray[0]));
			response.setHttpStatusResponse(responseArray[1]);
		}
		return response;
	}

	public SendCommandToDeviceResponse asyncSendCommandToDevice(SendCommandToDeviceRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] paramBody = request.getBodyAndParams();
			String[] responseArray = asyncClient.post(configParameters.getDevicesURLExtension(),
					paramBody[0],
					paramBody[1]);
			SendCommandToDeviceResponse response = SendCommandToDeviceResponse.Builder.newBuilder().build();
			if (responseArray[0] != null) {
				response.setHttpStatusCode(Integer.parseInt(responseArray[0]));
				response.setHttpStatusResponse(responseArray[1]);
			}
			return response;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public DevicesListResponse listDevices(DevicesListRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] responseArray = syncClient.get(configParameters.getDevicesURLExtension(), request.getParamsForList(),
				false);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				return DevicesListResponse.Builder.newBuilder().buildResponse(responseArray[2]).build();
			}
		}
		return null;
	}

	public DevicesListResponse asyncListDevices(DevicesListRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] responseArray = asyncClient.get(configParameters.getDevicesURLExtension(),
					request.getParamsForList());
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					return DevicesListResponse.Builder.newBuilder().buildResponse(responseArray[2]).build();
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public DeviceConfig modifyCloudToDeviceConfig(ModifyCloudToDeviceConfigRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] paramBody = request.getBodyAndParams();
		String[] responseArray = syncClient.post(configParameters.getDevicesURLExtension(), paramBody[0],
				paramBody[1], false);
		DeviceConfig deviceConfig = DeviceConfig.newBuilder().build();
		if (responseArray[0] != null) {
			if (Integer.parseInt(responseArray[0]) == 200)
				deviceConfig.loadFromString(responseArray[2]);
		}
		return deviceConfig;
	}

	public DeviceConfig asyncModifyCloudToDeviceConfig(ModifyCloudToDeviceConfigRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] paramBody = request.getBodyAndParams();
			String[] responseArray = asyncClient.post(
					configParameters.getDevicesURLExtension(), paramBody[0],
					paramBody[1]);
			DeviceConfig deviceConfig = DeviceConfig.newBuilder().build();
			if (responseArray[0] != null) {
				if (Integer.parseInt(responseArray[0]) == 200)
					deviceConfig.loadFromString(responseArray[2]);
			}
			return deviceConfig;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public ListDeviceStatesResponse listDeviceStates(ListDeviceStatesRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] responseArray = syncClient.get(configParameters.getDevicesStatesURLExtension(),
				request.getParamsForList(), false);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				return ListDeviceStatesResponse.Builder.newBuilder().buildResponse(responseArray[2]).build();
			}
		}
		return null;
	}

	public ListDeviceStatesResponse asyncListDeviceStates(ListDeviceStatesRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] responseArray = asyncClient.get(configParameters.getDevicesStatesURLExtension(),
					request.getParamsForList());
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					return ListDeviceStatesResponse.Builder.newBuilder().buildResponse(responseArray[2]).build();
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public ListDeviceConfigVersionsResponse listDeviceConfigVersions(ListDeviceConfigVersionsRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] responseArray = syncClient.get(configParameters.getCloudiotConfigURLExtension(),
				request.getParamsForList(), false);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				return ListDeviceConfigVersionsResponse.Builder.newBuilder().buildResponse(responseArray[2]).build();
			}
		}
		return null;
	}

	public ListDeviceConfigVersionsResponse asyncListDeviceConfigVersions(ListDeviceConfigVersionsRequest request) {
		try {
			AsyncClient asyncClient = new AsyncClient();
			String[] responseArray = asyncClient.get(
					configParameters.getCloudiotConfigURLExtension(),
					request.getParamsForList());
			if (responseArray[0] != null) {
				int responseCode = Integer.parseInt(responseArray[0]);
				if (responseCode == 200) {
					return ListDeviceConfigVersionsResponse.Builder.newBuilder().buildResponse(responseArray[2]).build();
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public ListDeviceRegistriesResponse listDeviceRegistries(ListDeviceRegistriesRequest request) {
		SyncClient syncClient = new SyncClient();
		String[] responseArray = syncClient.get(configParameters.getCloudiotURLExtension(), request.getParamsForList(),
				true);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				return ListDeviceRegistriesResponse.Builder.newBuilder().buildResponse(responseArray[2]).build();
			}
		}
		return null;
	}

	public ListDeviceRegistriesResponse asyncListDeviceRegistries(ListDeviceRegistriesRequest request) {
		AsyncClient asyncClient = new AsyncClient();
		String[] responseArray = asyncClient.asyncListDeviceRegistries(configParameters.getCloudiotURLExtension(),
				request.getParamsForList(), true);
		if (responseArray[0] != null) {
			int responseCode = Integer.parseInt(responseArray[0]);
			if (responseCode == 200) {
				return ListDeviceRegistriesResponse.Builder.newBuilder().buildResponse(responseArray[2]).build();
			}
		}
		return null;
	}
}

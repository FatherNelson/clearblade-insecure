package com.clearblade.cloud.iot.v1;

import java.util.logging.Logger;

import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayRequest;
import com.clearblade.cloud.iot.v1.binddevicetogateway.BindDeviceToGatewayResponse;
import com.clearblade.cloud.iot.v1.createdevice.CreateDeviceRequest;
import com.clearblade.cloud.iot.v1.createdeviceregistry.CreateDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.deletedevice.DeleteDeviceRequest;
import com.clearblade.cloud.iot.v1.deletedeviceregistry.DeleteDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.deviceslist.DevicesListRequest;
import com.clearblade.cloud.iot.v1.deviceslist.DevicesListResponse;
import com.clearblade.cloud.iot.v1.devicestateslist.ListDeviceStatesRequest;
import com.clearblade.cloud.iot.v1.devicestateslist.ListDeviceStatesResponse;
import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.devicetypes.DeviceConfig;
import com.clearblade.cloud.iot.v1.getdevice.GetDeviceRequest;
import com.clearblade.cloud.iot.v1.getdeviceregistry.GetDeviceRegistryRequest;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsRequest;
import com.clearblade.cloud.iot.v1.listdeviceconfigversions.ListDeviceConfigVersionsResponse;
import com.clearblade.cloud.iot.v1.listdeviceregistries.ListDeviceRegistriesRequest;
import com.clearblade.cloud.iot.v1.listdeviceregistries.ListDeviceRegistriesResponse;
import com.clearblade.cloud.iot.v1.modifycloudtodeviceconfig.ModifyCloudToDeviceConfigRequest;
import com.clearblade.cloud.iot.v1.registrytypes.DeviceRegistry;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceRequest;
import com.clearblade.cloud.iot.v1.sendcommandtodevice.SendCommandToDeviceResponse;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayRequest;
import com.clearblade.cloud.iot.v1.unbinddevicefromgateway.UnbindDeviceFromGatewayResponse;
import com.clearblade.cloud.iot.v1.updatedevice.UpdateDeviceRequest;
import com.clearblade.cloud.iot.v1.updatedeviceregistry.UpdateDeviceRegistryRequest;

public class DeviceManagerAsyncClient {

	static Logger log = Logger.getLogger(DeviceManagerAsyncClient.class.getName());

	/**
	 * Calling getDevice api
	 * 
	 * @param request
	 * @return Device object
	 */

	public Device getDevice(GetDeviceRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncGetDevice(request);
	}

	/**
	 * Calling createDevice api
	 * 
	 * @param request
	 * @return Device object
	 */
	public Device createDevice(CreateDeviceRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncCreateDevice(request, null);
	}

	public Device createDevice(CreateDeviceRequest request, ResponseCallback callback) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncCreateDevice(request, callback);
	}

	/**
	 * Calling deleteDevice api
	 * 
	 * @param request
	 */
	public void deleteDevice(DeleteDeviceRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		cbManager.asyncDeleteDevice(request);
	}

	/**
	 * Calling updateDevice api
	 * 
	 * @param request
	 * @return Device object
	 */
	public Device updateDevice(UpdateDeviceRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncUpdateDevice(request);
	}

	/**
	 * Calling bindDeviceToGateway api
	 * 
	 * @param request
	 * @return
	 */
	public BindDeviceToGatewayResponse bindDeviceToGateway(BindDeviceToGatewayRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncBindDeviceToGateway(request);
	}

	/**
	 * Calling unbindDeviceFromGateway api
	 * 
	 * @param request
	 * @return
	 */
	public UnbindDeviceFromGatewayResponse unbindDeviceFromGateway(UnbindDeviceFromGatewayRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncUnbindDeviceFromGateway(request);
	}

	public SendCommandToDeviceResponse sendCommandToDevice(SendCommandToDeviceRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncSendCommandToDevice(request);

	}

	public DeviceConfig modifyCloudToDeviceConfig(ModifyCloudToDeviceConfigRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncModifyCloudToDeviceConfig(request);
	}

	public DevicesListResponse listDevices(DevicesListRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncListDevices(request);
	}

	public ListDeviceStatesResponse listDeviceStates(ListDeviceStatesRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncListDeviceStates(request);
	}

	public ListDeviceConfigVersionsResponse listDeviceConfigVersions(ListDeviceConfigVersionsRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncListDeviceConfigVersions(request);
	}

	// Registry Apis
	/**
	 * Calling getDevice api
	 * 
	 * @param request
	 * @return Device object
	 */

	public DeviceRegistry getDeviceRegistry(GetDeviceRegistryRequest request) {
		ClearBladeRegistryManager cbManager = new ClearBladeRegistryManager();
		return cbManager.asyncGetDeviceRegistry(request);
	}

	public DeviceRegistry createDeviceRegistry(CreateDeviceRegistryRequest request) {
		ClearBladeRegistryManager cbManager = new ClearBladeRegistryManager();
		return cbManager.asyncCreateDeviceRegistry(request);
	}

	public DeviceRegistry updateDeviceRegistry(UpdateDeviceRegistryRequest request) {
		ClearBladeRegistryManager cbManager = new ClearBladeRegistryManager();
		return cbManager.asyncUpdateDeviceRegistry(request);
	}

	public void deleteDeviceRegistry(DeleteDeviceRegistryRequest request) {
		ClearBladeRegistryManager cbManager = new ClearBladeRegistryManager();
		cbManager.asyncDeleteDeviceRegistry(request);
	}

	public ListDeviceRegistriesResponse listDeviceRegistries(ListDeviceRegistriesRequest request) {
		ClearBladeDeviceManager cbManager = new ClearBladeDeviceManager();
		return cbManager.asyncListDeviceRegistries(request);
	}

}

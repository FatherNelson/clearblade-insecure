# Changelog

## 1.0.6
- Added environment variables to improve performance as the REST call can be prevented by passing the API keys as environment variables.

## 1.0.5
- Added interface for DeviceManagerClient

## 1.0.4
- Added fieldMask parameter in ListDeviceRegistriesRequest
- Updated fieldMask to support URL encoding

## 1.0.3
- Support % in character in device ID

## 1.0.2

- Replace URL string with URLEncoded to support % in a device name.

## 1.0.1

- Convert URL string to URLEncoded to support % in a device name.

## 1.0.0

- Updated UpdateDeviceRequest, to set name as DeviceName instead of only device_id. See below example,
  UpdateDeviceRequest.Builder.newBuilder().setName(**DeviceName.of(PROJECT,LOCATION,REGISTRY,DEVICE).toString()**).setDevice(device.build())
- Removed static variables from ConfigParameters and its dependency.
- Updated CreateDeviceRequest.java to support parent as String instead of RegistryName. See below example,

CreateDeviceRequest.Builder.newBuilder()**.setParent(RegistryName.of(PROJECT, LOCATION, REGISTRY).toString())**.setDevice(device).build();

## 0.1.5

- Minor fixes

## 0.1.4

- Minor fixes

## 0.1.3

- Minor fixes

## 0.1.2

- Minor fixes

## 0.1.1

- Minor fixes

## 0.1.0

- Minor fixes

## 0.0.1

- Initial release of clearblade-cloud-iot

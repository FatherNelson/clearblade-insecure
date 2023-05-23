# Changelog

## 1.0.0

- Updated UpdateDeviceRequest, to set name as DeviceName instead of only device_id. See below example, 
   UpdateDeviceRequest.Builder.newBuilder().setName(**DeviceName.of(PROJECT,LOCATION,REGISTRY,DEVICE).toString()**).setDevice(device.build())
- Removed static variables from ConfigParameters and it's dependency.
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

package com.clearblade.cloud.iot.v1.createdevice;

import com.clearblade.cloud.iot.v1.devicetypes.Device;
import com.clearblade.cloud.iot.v1.registrytypes.RegistryName;

public class CreateDeviceRequest {
	private final RegistryName parent;
	private final Device device;

	private CreateDeviceRequest(Builder builder) {
		this.parent = builder.parent;
		this.device = builder.device;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private RegistryName parent;
		private Device device;

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder setParent(RegistryName parent) {
			this.parent = parent;
			return this;
		}

		public Builder setDevice(Device device) {
			this.device = device;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public CreateDeviceRequest build() {
			return new CreateDeviceRequest(this);
		}
	}

	public String[] getParams() {
		String[] params = new String[2];
		params[0] = "parent=" + this.parent.getRegistryFullName();
		params[1] = this.device.createDeviceJSONObject();
		return params;
	}
}

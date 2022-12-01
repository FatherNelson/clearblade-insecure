package com.clearblade.cloud.iot.v1.deletedevice;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;

public class DeleteDeviceRequest {
	private DeviceName name;

	private DeleteDeviceRequest(Builder builder) {
		this.name = builder.name;
	}

	
	public DeviceName getName() {
		return name;
	}

	public void setName(DeviceName name) {
		this.name = name;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private DeviceName name;

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// build method to deal with outer class
		// to return outer instance
		public DeleteDeviceRequest build() {
			return new DeleteDeviceRequest(this);
		}

		// Setter methods
		public Builder setName(DeviceName name) {
			this.name = name;
			return this;
		}
	}

	@Override
	public String toString() {
		String params = "";
		params = "name="+this.name.toString();
		return params;
	}

}

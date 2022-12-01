package com.clearblade.cloud.iot.v1.getdevice;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.devicetypes.FieldMask;

public class GetDeviceRequest {

	private final DeviceName name;
	private final FieldMask fieldMask;

	private GetDeviceRequest(Builder builder) {
		this.name = builder.name;
		this.fieldMask = builder.fieldMask;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private DeviceName name;
		private FieldMask fieldMask;

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public FieldMask getFieldMask() {
			return fieldMask;
		}

		public Builder setFieldMask(FieldMask fieldMask) {
			this.fieldMask = fieldMask;
			return this;
		}

		public DeviceName getName() {
			return name;
		}

		public Builder setName(DeviceName name) {
			this.name = name;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public GetDeviceRequest build() {
			return new GetDeviceRequest(this);
		}
	}

	@Override
	public String toString() {
		String params = "";
		params = "name=" + this.name.toString();
		if (this.fieldMask.toString() != "") {
			params += "&fieldMask=" + this.fieldMask.toString();
		}
		return params;
	}

}

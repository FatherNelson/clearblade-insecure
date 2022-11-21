package com.clearblade.cloud.iot.v1.modifycloudtodeviceconfig;

import java.util.Arrays;

import org.json.simple.JSONObject;

import com.clearblade.cloud.iot.v1.utils.ByteString;
import com.clearblade.cloud.iot.v1.utils.Constants;

public class ModifyCloudToDeviceConfigRequest {

	private String deviceName;
	private String versionToUpdate;
	private ByteString binaryData;
	private byte[] binaryDataByte;

	private ModifyCloudToDeviceConfigRequest(Builder builder) {
		this.deviceName = builder.deviceName;
		this.versionToUpdate = builder.versionToUpdate;
		this.binaryData = builder.binaryData;
		this.binaryDataByte = builder.binaryDataByte;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private String deviceName;
		private String versionToUpdate;
		private ByteString binaryData;
		private byte[] binaryDataByte;

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder setName(String deviceName) {
			this.deviceName = deviceName;
			return this;
		}

		public Builder setVersionToUpdate(String versionToUpdate) {
			this.versionToUpdate = versionToUpdate;
			return this;
		}

		public Builder setBinaryData(ByteString binaryData) {
			this.binaryData = binaryData;
			return this;
		}

		public Builder setBinaryData(byte[] binaryDataByte) {
			this.binaryDataByte = binaryDataByte;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public ModifyCloudToDeviceConfigRequest build() {
			return new ModifyCloudToDeviceConfigRequest(this);
		}
	}

	@Override
	public String toString() {
		return "name = " + this.deviceName + ", versionToUpdate = " + this.versionToUpdate;
	}

	@SuppressWarnings("unchecked")
	public String[] getBodyAndParams() {
		String[] output = new String[2];
		String params = "name=" + this.deviceName + "&method=" + Constants.MODIFY_CLOUD_TO_DEVICE_CONFIG;
		String bData = "EMPTY";
		if (this.binaryData != null) {
			bData = new String(this.binaryData.getBinaryDataArray());
		} else if (this.binaryDataByte != null) {
			if (this.binaryDataByte.length == 0) {
				bData = "EMPTY";
			} else {
				bData = Arrays.toString(this.binaryDataByte);
			}
		}
		JSONObject bodyParams = new JSONObject();
		bodyParams.put("binaryData", bData);
		bodyParams.put("versionToUpdate", this.versionToUpdate);

		output[0] = params;
		output[1] = bodyParams.toJSONString();
		return output;
	}
}

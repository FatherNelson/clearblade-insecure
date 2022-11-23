package com.clearblade.cloud.iot.v1.sendcommandtodevice;

import java.util.Arrays;

import org.json.simple.JSONObject;

import com.clearblade.cloud.iot.v1.devicetypes.DeviceName;
import com.clearblade.cloud.iot.v1.utils.ByteString;
import com.clearblade.cloud.iot.v1.utils.Constants;

public class SendCommandToDeviceRequest {

	private final DeviceName name;
	private final ByteString binaryData;
	private final byte[] binaryDataByte;
	private final String subfolder;
	private final String deviceName;
	public JSONObject requestParams;
	public JSONObject bodyParams;

	private SendCommandToDeviceRequest(Builder builder) {
		this.name = builder.name;
		this.binaryData = builder.binaryData;
		this.subfolder = builder.subfolder;
		this.binaryDataByte = builder.binaryDataByte;
		this.deviceName = builder.deviceName;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private DeviceName name;
		private ByteString binaryData;
		private String subfolder;
		private byte[] binaryDataByte;
		private String deviceName;

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder setName(DeviceName name) {
			this.name = name;
			return this;
		}

		public Builder setBinaryData(ByteString binaryData) {
			this.binaryData = binaryData;
			return this;
		}

		public Builder setBinaryDataByte(byte[] binaryDataByte) {
			this.binaryDataByte = binaryDataByte;
			return this;
		}

		public Builder setSubfolder(String subfolder) {
			this.subfolder = subfolder;
			return this;
		}

		public Builder setName(String deviceName) {
			this.deviceName = deviceName;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public SendCommandToDeviceRequest build() {
			return new SendCommandToDeviceRequest(this);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		requestParams = new JSONObject();
		bodyParams = new JSONObject();

		String dName = "";
		String bData = null;

		if (this.name != null) {
			dName = this.name.getDevice();
		} else if (this.deviceName != null) {
			dName = this.deviceName;
		}
		if (this.binaryData != null) {
			bData = new String(this.binaryData.getBinaryDataArray());
		} else if (this.binaryDataByte != null) {
			if (this.binaryDataByte.length == 0) {
				bData = "EMPTY";
			} else {
				bData = Arrays.toString(this.binaryDataByte);
			}
		}

		requestParams.put("name", dName);
		bodyParams.put("binaryData", bData);
		bodyParams.put("subfolder", subfolder);

		return "name = " + dName + ", binaryData = " + bData + ", subfolder = " + subfolder;

	}

	@SuppressWarnings("unchecked")
	public String[] getBodyAndParams() {
		String[] output = new String[2];
		String params = "name=" + this.deviceName + "&method=" + Constants.SEND_COMMAND_TO_DEVICE;
		String bData = null;
		if (this.binaryData != null) {
			bData = new String(this.binaryData.getBinaryDataArray());
		} else if (this.binaryDataByte != null) {
			if (this.binaryDataByte.length == 0) {
				bData = "EMPTY";
			} else {
				bData = Arrays.toString(this.binaryDataByte);
			}
		}
		bodyParams = new JSONObject();
		bodyParams.put("binaryData", bData);
		if (this.subfolder != null) {
			bodyParams.put("subfolder", this.subfolder);
		}

		output[0] = params;
		output[1] = bodyParams.toJSONString();
		return output;
	}
}

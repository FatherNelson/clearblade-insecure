package com.clearblade.cloud.iot.v1;

import java.util.Arrays;

import com.clearblade.cloud.iot.v1.utils.ByteString;
import com.clearblade.cloud.iot.v1.utils.DeviceName;

public class SendCommandToDeviceRequest {

	private final DeviceName name;
	private final ByteString binaryData;
	private final byte[] binaryDataByte;
	private final String subfolder;
	private final String deviceName;

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

		public Builder setDeviceName(String deviceName) {
			this.deviceName = deviceName;
			return this;
		}
		
		// build method to deal with outer class
		// to return outer instance
		public SendCommandToDeviceRequest build() {
			return new SendCommandToDeviceRequest(this);
		}
	}

	@Override
	public String toString() {
		String requestParams="";
		String bodyParams="";
		if(this.name != null) {
			requestParams = requestParams.concat("name = " + this.name.getDevice());
		}else if(this.deviceName != null) {
			requestParams = requestParams.concat("deviceName = "+this.deviceName);
		}
		if(this.binaryData != null) {
			bodyParams = bodyParams.concat(",binaryData=");
			bodyParams = bodyParams.concat(new String(this.binaryData.getBinaryDataArray()));
		}else if(this.binaryDataByte != null) {
			if(this.binaryDataByte.length == 0){
				bodyParams = bodyParams.concat(",binaryDataByte=EMPTY");
			}else {
				bodyParams = bodyParams.concat(",binaryDataByte="+Arrays.toString(this.binaryDataByte));
			}
		}
		bodyParams = bodyParams.concat(",subfolder = "+this.subfolder);
		
		return requestParams.concat(bodyParams);
	}
}

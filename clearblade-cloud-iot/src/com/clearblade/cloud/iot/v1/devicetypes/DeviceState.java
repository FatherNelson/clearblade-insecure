package com.clearblade.cloud.iot.v1.devicetypes;

public class DeviceState {
	private String updateTime;
	private String binaryData;

	public DeviceState(Builder builder) {
		updateTime = builder.updateTime;
		binaryData = builder.getBinaryData();
	}

	public DeviceState() {

	}

	public String getBinaryData() {
		return binaryData;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setBinaryData(String binaryData) {
		this.binaryData = binaryData;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private String binaryData;
		private String updateTime;

		protected Builder() {
		}

		public String getUpdateTime() {
			return updateTime;
		}

		public Builder setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
			return this;
		}

		public String getBinaryData() {
			return binaryData;
		}

		public Builder setBinaryData(String binaryData) {
			this.binaryData = binaryData;
			return this;
		}

		private Builder(DeviceState deviceState) {
			this.updateTime = deviceState.updateTime;
			this.binaryData = deviceState.binaryData;
		}

		public DeviceState build() {
			return new DeviceState(this);
		}
	}

	@Override
	public String toString() {
		String stateStr = "";
		if (this.getUpdateTime() != null) {
			stateStr += "{\"updateTime\":\"" + this.getUpdateTime() + "\",";
		} else {
			stateStr += "{\"updateTime\":\"\",";
		}
		if (this.getBinaryData() != null) {
			stateStr += "\"binaryData\":\"" + this.getBinaryData() + "\"}";
		} else {
			stateStr += "\"binaryData\":\"\"}";
		}
		return stateStr;
	}
}

package com.clearblade.cloud.iot.v1.devicetypes;

import org.json.simple.JSONObject;

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

	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();
		if (this.getUpdateTime() != null) {
			jsonObject.put("updateTime", this.getUpdateTime());
		} else {
			jsonObject.put("updateTime", "");
		}
		if (this.getBinaryData() != null) {
			jsonObject.put("binaryData", this.getBinaryData());
		} else {
			jsonObject.put("binaryData", "");
		}
		return jsonObject;
	}
}

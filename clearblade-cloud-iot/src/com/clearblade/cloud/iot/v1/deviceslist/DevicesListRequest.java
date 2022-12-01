package com.clearblade.cloud.iot.v1.deviceslist;

import com.clearblade.cloud.iot.v1.devicetypes.GatewayListOptions;

public class DevicesListRequest {

	private String parent;
	private String deviceNumIds;
	private String deviceIds;
	private String fieldMask;
	private int pageSize = -1;
	private String pageToken;
	private GatewayListOptions gatewayListOptions;

	private DevicesListRequest(Builder builder) {
		this.parent = builder.parent;
		this.deviceNumIds = builder.deviceNumIds;
		this.deviceIds = builder.deviceIds;
		this.fieldMask = builder.fieldMask;
		this.pageSize = builder.pageSize;
		this.pageToken = builder.pageToken;
		this.gatewayListOptions = builder.gatewayListOptions;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private String parent;
		private String deviceNumIds;
		private String deviceIds;
		private String fieldMask;
		private int pageSize;
		private String pageToken;
		private GatewayListOptions gatewayListOptions;

		public static Builder newBuilder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder setParent(String parent) {
			this.parent = parent;
			return this;
		}

		public Builder setDeviceNumIds(String deviceNumIds) {
			this.deviceNumIds = deviceNumIds;
			return this;
		}

		public Builder setDeviceIds(String deviceIds) {
			this.deviceIds = deviceIds;
			return this;
		}

		public Builder setFieldMask(String fieldMask) {
			this.fieldMask = fieldMask;
			return this;
		}

		public Builder setPageSize(int pageSize) {
			this.pageSize = pageSize;
			return this;
		}

		public Builder setPageToken(String pageToken) {
			this.pageToken = pageToken;
			return this;
		}

		public Builder setGatewayListOptions(GatewayListOptions gatewayListOptions) {
			this.gatewayListOptions = gatewayListOptions;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public DevicesListRequest build() {
			return new DevicesListRequest(this);
		}

	}

	@Override
	public String toString() {
		return this.parent.toString();
	}

	public String getParamsForList() {
		String params = "";
		params = "parent=" + this.parent;
		if (this.deviceNumIds != null)
			params += "&deviceNumIds=" + this.deviceNumIds;
		if (this.deviceIds != null)
			params += "&deviceIds=" + this.deviceIds;
		if (this.fieldMask != null)
			params += "&fieldMask=" + this.fieldMask;
		if (this.pageSize > 0)
			params += "&pageSize=" + String.valueOf(this.pageSize);
		if (this.pageToken != null)
			params += "&pageToken=" + this.pageToken;
		if (this.gatewayListOptions != null) {
			if (this.gatewayListOptions.getGatewayType() != null) {
				params += "&gatewayListOptions.gatewayType=" + this.gatewayListOptions.getGatewayType().name();
			} else if (this.gatewayListOptions.getAssociationsDeviceId() != null) {
				params += "&gatewayListOptions.associationsDeviceId="
						+ this.gatewayListOptions.getAssociationsDeviceId();
			} else if (this.gatewayListOptions.getAssociationsGatewayId() != null) {
				params += "&gatewayListOptions.associationsGatewayId="
						+ this.gatewayListOptions.getAssociationsGatewayId();
			}

		}

		return params;
	}

}

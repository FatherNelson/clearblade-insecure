package com.clearblade.cloud.iot.v1.devicetypes;

public class GatewayListOptions {

	private GatewayType gatewayType;
	private String associationsGatewayId;
	private String associationsDeviceId;

	public GatewayListOptions() {
	}

	public GatewayType getGatewayType() {
		return gatewayType;
	}

	public String getAssociationsGatewayId() {
		return associationsGatewayId;
	}

	public String getAssociationsDeviceId() {
		return associationsDeviceId;
	}

	public void setGatewayType(GatewayType gatewayType) {
		this.gatewayType = gatewayType;
	}

	public void setAssociationsGatewayId(String associationsGatewayId) {
		this.associationsGatewayId = associationsGatewayId;
	}

	public void setAssociationsDeviceId(String associationsDeviceId) {
		this.associationsDeviceId = associationsDeviceId;
	}

	private GatewayListOptions(Builder builder) {
		gatewayType = builder.gatewayType;
		associationsDeviceId = builder.associationsDeviceId;
		associationsGatewayId = builder.associationsGatewayId;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private GatewayType gatewayType;
		private String associationsGatewayId;
		private String associationsDeviceId;

		protected Builder() {
		}

		private Builder(GatewayListOptions gatewayListOptions) {
			this.gatewayType = gatewayListOptions.gatewayType;
			this.associationsDeviceId = gatewayListOptions.associationsDeviceId;
			this.associationsGatewayId = gatewayListOptions.associationsGatewayId;
		}

		public Builder setGatewayType(GatewayType gatewayType) {
			this.gatewayType = gatewayType;
			return this;
		}

		public Builder setAssociationsGatewayId(String associationsGatewayId) {
			this.associationsGatewayId = associationsGatewayId;
			return this;
		}

		public Builder setAssociationsDeviceId(String associationsDeviceId) {
			this.associationsDeviceId = associationsDeviceId;
			return this;
		}

		public GatewayListOptions build() {
			return new GatewayListOptions(this);
		}
	}

}

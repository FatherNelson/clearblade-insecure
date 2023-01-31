package com.clearblade.cloud.iot.v1.devicetypes;

import org.json.simple.JSONObject;

public class GatewayConfig {

	private GatewayType gatewayType;
	private GatewayAuthMethod gatewayAuthMethod;
	private String lastAccessedGatewayId;
	private String lastAccessedGatewayTime;

	public GatewayConfig() {
	}

	public GatewayType getGatewayType() {
		return gatewayType;
	}

	public GatewayAuthMethod getGatewayAuthMethod() {
		return gatewayAuthMethod;
	}

	public String getLastAccessedGatewayId() {
		return lastAccessedGatewayId;
	}

	public String getLastAccessedGatewayTime() {
		return lastAccessedGatewayTime;
	}

	public void setGatewayType(GatewayType gatewayType) {
		this.gatewayType = gatewayType;
	}

	public void setGatewayAuthMethod(GatewayAuthMethod gatewayAuthMethod) {
		this.gatewayAuthMethod = gatewayAuthMethod;
	}

	public void setLastAccessedGatewayId(String lastAccessedGatewayId) {
		this.lastAccessedGatewayId = lastAccessedGatewayId;
	}

	public void setLastAccessedGatewayTime(String lastAccessedGatewayTime) {
		this.lastAccessedGatewayTime = lastAccessedGatewayTime;
	}

	private GatewayConfig(Builder builder) {
		gatewayType = builder.gatewayType;
		gatewayAuthMethod = builder.gatewayAuthMethod;
		lastAccessedGatewayId = builder.lastAccessedGatewayId;
		lastAccessedGatewayTime = builder.lastAccessedGatewayTime;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private GatewayType gatewayType;
		private GatewayAuthMethod gatewayAuthMethod;
		private String lastAccessedGatewayId;
		private String lastAccessedGatewayTime;

		protected Builder() {
		}

		private Builder(GatewayConfig gatewayConfig) {
			this.gatewayType = gatewayConfig.gatewayType;
			this.gatewayAuthMethod = gatewayConfig.gatewayAuthMethod;
			this.lastAccessedGatewayId = gatewayConfig.lastAccessedGatewayId;
			this.lastAccessedGatewayTime = gatewayConfig.lastAccessedGatewayTime;
		}

		public GatewayConfig build() {
			return new GatewayConfig(this);
		}
	}

//	@Override
//	public String toString() {
//		String gatewayCfgStr = "";
//
//		if (this.getGatewayAuthMethod() != null) {
//			gatewayCfgStr += "{\"gatewayAuthMethod\":\"" + this.getGatewayAuthMethod().name() + "\",";
//		} else {
//			gatewayCfgStr += "{\"gatewayAuthMethod\":\"" + GatewayAuthMethod.GATEWAY_AUTH_METHOD_UNSPECIFIED + "\",";
//		}
//		if (this.getGatewayType() != null) {
//			gatewayCfgStr += "\"gatewayType\":\"" + this.getGatewayType().name() + "\",";
//		} else {
//			gatewayCfgStr += "\"gatewayType\":\"" + GatewayType.valueOf(GatewayType.NON_GATEWAY_VALUE) + "\",";
//		}
//		if (this.getLastAccessedGatewayId() != null) {
//			gatewayCfgStr += "\"lastAccessedGatewayId\":\"" + this.getLastAccessedGatewayId() + "\",";
//		} else {
//			gatewayCfgStr += "\"lastAccessedGatewayId\":\"\",";
//		}
//		if (this.getLastAccessedGatewayTime() != null) {
//			gatewayCfgStr += "\"lastAccessedGatewayTime\":\"" + this.getLastAccessedGatewayTime() + "\"}";
//		} else {
//			gatewayCfgStr += "\"lastAccessedGatewayTime\":\"\"}";
//		}
//		return gatewayCfgStr;
//
//	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();
		if (this.getGatewayAuthMethod() != null) {
			jsonObject.put("gatewayAuthMethod", this.getGatewayAuthMethod().name());
		} else {
			jsonObject.put("gatewayAuthMethod", GatewayAuthMethod.GATEWAY_AUTH_METHOD_UNSPECIFIED.toString());
		}
		if (this.getGatewayType() != null) {
			jsonObject.put("gatewayType", this.getGatewayType().name());
		} else {
			jsonObject.put("gatewayType", GatewayType.valueOf(GatewayType.NON_GATEWAY_VALUE));
		}
		if (this.getLastAccessedGatewayId() != null) {
			jsonObject.put("gatewayAuthMethod", this.getLastAccessedGatewayId());
		} else {
			jsonObject.put("gatewayAuthMethod", "");
		}
		if (this.getLastAccessedGatewayTime() != null) {
			jsonObject.put("lastAccessedGatewayTime", this.getLastAccessedGatewayTime());
		} else {
			jsonObject.put("lastAccessedGatewayTime", "");
		}
		return jsonObject;
	}

}

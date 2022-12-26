package com.clearblade.cloud.iot.v1.devicetypes;

import org.json.simple.JSONObject;

import com.clearblade.cloud.iot.v1.registrytypes.PublicKeyCredential;

public class DeviceCredential {

	private String expirationTime = "";
	private PublicKeyCredential publicKey;

	public DeviceCredential() {
	}

	private DeviceCredential(Builder builder) {
		expirationTime = builder.expirationTime;
		publicKey = builder.publicKey;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private String expirationTime;
		private PublicKeyCredential publicKey;

		protected Builder() {

		}

		public String getExpirationTime() {
			return expirationTime;
		}

		public Builder setExpirationTime(String expirationTime) {
			this.expirationTime = expirationTime;
			return this;
		}

		public PublicKeyCredential getPublicKey() {
			return publicKey;
		}

		public Builder setPublicKey(PublicKeyCredential publicKey) {
			this.publicKey = publicKey;
			return this;
		}

		private Builder(DeviceCredential deviceCredential) {
			this.expirationTime = deviceCredential.expirationTime;
			this.publicKey = deviceCredential.publicKey;
		}

		public DeviceCredential build() {
			return new DeviceCredential(this);
		}
	}

	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

	public void setPublicKey(PublicKeyCredential publicKey) {
		this.publicKey = publicKey;
	}

	public boolean isEmpty() {
		return publicKey == null;
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("expirationTime", this.expirationTime);
		jsonObject.put("publicKey", this.publicKey.toJSONObject());
		return jsonObject;
	}
}

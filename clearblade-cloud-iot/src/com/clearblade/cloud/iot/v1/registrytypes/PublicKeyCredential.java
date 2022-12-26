package com.clearblade.cloud.iot.v1.registrytypes;

import org.json.simple.JSONObject;

public class PublicKeyCredential {

	private PublicKeyFormat format;
	private String key;

	public PublicKeyCredential() {
	}

	public void setFormat(PublicKeyFormat format) {
		this.format = format;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private PublicKeyCredential(Builder builder) {
		format = builder.format;
		key = builder.key;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public static class Builder {
		private PublicKeyFormat format;
		private String key;

		protected Builder() {

		}

		public PublicKeyFormat getFormat() {
			return format;
		}

		public Builder setFormat(PublicKeyFormat format) {
			this.format = format;
			return this;
		}

		public String getKey() {
			return key;
		}

		public Builder setKey(String key) {
			this.key = key;
			return this;
		}

		private Builder(PublicKeyCredential publicKeyCredential) {
			this.format = publicKeyCredential.format;
			this.key = publicKeyCredential.key;
		}

		public PublicKeyCredential build() {
			return new PublicKeyCredential(this);
		}
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("format", this.format.name());
		jsonObject.put("key", this.key);
		return jsonObject;
	}

}

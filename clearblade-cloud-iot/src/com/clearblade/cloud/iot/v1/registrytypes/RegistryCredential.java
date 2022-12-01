package com.clearblade.cloud.iot.v1.registrytypes;

import org.json.simple.JSONObject;

public class RegistryCredential {

	private PublicKeyCertificate publicKeyCertificate;

	public RegistryCredential() {
		publicKeyCertificate = null;
	}

	private RegistryCredential(Builder builder) {
		publicKeyCertificate = builder.getPublicKeyCertificate();
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	/**
	 * Builder for setting
	 * RegistryCredential
	 */
	public static class Builder {
		private PublicKeyCertificate publicKeyCertificate;

		protected Builder() {

		}

		public PublicKeyCertificate getPublicKeyCertificate() {
			return publicKeyCertificate;
		}

		public Builder setPublicKeyCertificate(PublicKeyCertificate publicKeyCertificate) {
			this.publicKeyCertificate = publicKeyCertificate;
			return this;
		}

		private Builder(RegistryCredential registryCredential) {
			this.publicKeyCertificate = registryCredential.publicKeyCertificate;
		}

		public RegistryCredential build() {
			return new RegistryCredential(this);
		}
	}

	@Override
	public String toString() {
		return "PublicKeyCertificate=" + this.publicKeyCertificate.toString();
	}

	public void loadFromJson(JSONObject jsonObject) {
		if (jsonObject.containsKey("publicKeyCertificate")) {
			PublicKeyCertificate publicKeyCertificateObj = new PublicKeyCertificate();
			JSONObject publicKeyCertificateJson = (JSONObject) jsonObject.get("publicKeyCertificate");
			if (publicKeyCertificateJson.containsKey("format"))
				publicKeyCertificateObj
						.setFormat(PublicKeyCertificateFormat.valueOf((String) publicKeyCertificateJson.get("format")));
			if (publicKeyCertificateJson.containsKey("certificate"))
				publicKeyCertificateObj
						.setCertificate((String) publicKeyCertificateJson.get("certificate"));
			if (publicKeyCertificateJson.containsKey("x509Details")) {
				JSONObject x509DJsonObject = (JSONObject) publicKeyCertificateJson.get("x509Details");
				X509CertificateDetails x509CertificateDetails = new X509CertificateDetails();
				x509CertificateDetails.setIssuer((String) x509DJsonObject.get("issuer"));
				x509CertificateDetails.setSubject((String) x509DJsonObject.get("subject"));
				x509CertificateDetails.setStartTime((String) x509DJsonObject.get("startTime"));
				x509CertificateDetails.setExpiryTime((String) x509DJsonObject.get("expiryTime"));
				x509CertificateDetails.setSignatureAlgorithm((String) x509DJsonObject.get("signatureAlgorithm"));
				x509CertificateDetails.setPublicKeyType((String) x509DJsonObject.get("publicKeyType"));
				publicKeyCertificateObj.setX509Details(x509CertificateDetails);
			}
			publicKeyCertificate = publicKeyCertificateObj;
		}
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJsonObject() {
		JSONObject json = new JSONObject();

		JSONObject publicKeyCertificateJson = new JSONObject();
		if (publicKeyCertificate.getFormat() != null)
			publicKeyCertificateJson.put("format", publicKeyCertificate.getFormat().name());
		if (publicKeyCertificate.getCertificate() != null)
			publicKeyCertificateJson.put("certificate", publicKeyCertificate.getCertificate());
		if (publicKeyCertificate.getX509Details() != null) {
			X509CertificateDetails x509CertificateDetails = publicKeyCertificate.getX509Details();
			JSONObject x509DJsonObject = new JSONObject();
			x509DJsonObject.put("issuer", x509CertificateDetails.getIssuer());
			x509DJsonObject.put("subject", x509CertificateDetails.getSubject());
			x509DJsonObject.put("startTime", x509CertificateDetails.getStartTime());
			x509DJsonObject.put("expiryTime", x509CertificateDetails.getExpiryTime());
			x509DJsonObject.put("signatureAlgorithm", x509CertificateDetails.getSignatureAlgorithm());
			x509DJsonObject.put("publicKeyType", x509CertificateDetails.getPublicKeyType());
			publicKeyCertificateJson.put("x509Details", x509DJsonObject);

		}
		json.put("publicKeyCertificate", publicKeyCertificateJson);
		return json;
	}
}

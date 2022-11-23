package com.clearblade.cloud.iot.v1.registrytypes;

public class PublicKeyCertificate {

	private PublicKeyCertificateFormat format;
	private String certificate;
	private X509CertificateDetails x509Details;

	public PublicKeyCertificateFormat getFormat() {
		return format;
	}

	public void setFormat(PublicKeyCertificateFormat format) {
		this.format = format;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public X509CertificateDetails getX509Details() {
		return x509Details;
	}

	public void setX509Details(X509CertificateDetails x509Details) {
		this.x509Details = x509Details;
	}

	@Override
	public String toString() {
		return "PublicKeyCertificateFormat=" + this.format.toString() + ",Certificate=" + certificate;
	}

}

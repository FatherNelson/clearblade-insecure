package com.clearblade.cloud.iot.v1.registrytypes;

public enum PublicKeyCertificateFormat {
	UNSPECIFIED_PUBLIC_KEY_CERTIFICATE_FORMAT(0), X509_CERTIFICATE_PEM(1), UNRECOGNIZED(-1),;

	public static final int UNSPECIFIED_PUBLIC_KEY_CERTIFICATE_FORMAT_VALUE = 0;
	public static final int X509_CERTIFICATE_PEM_VALUE = 1;

	public final int getNumber() {
		if (this == UNRECOGNIZED) {
			throw new java.lang.IllegalArgumentException("Can't get the number of an unknown enum value.");
		}
		return value;
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static PublicKeyCertificateFormat valueOf(int value) {
		return forNumber(value);
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static PublicKeyCertificateFormat forNumber(int value) {
		switch (value) {
			case 0:
				return UNSPECIFIED_PUBLIC_KEY_CERTIFICATE_FORMAT;
			case 1:
				return X509_CERTIFICATE_PEM;
			default:
				return null;
		}
	}

	public PublicKeyCertificateFormat findValueByNumber(int number) {
		return PublicKeyCertificateFormat.forNumber(number);
	}

	private final int value;

	private PublicKeyCertificateFormat(int value) {
		this.value = value;
	}

}

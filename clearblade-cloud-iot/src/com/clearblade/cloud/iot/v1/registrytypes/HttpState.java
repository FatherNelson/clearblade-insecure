package com.clearblade.cloud.iot.v1.registrytypes;

public enum HttpState {
	HTTP_STATE_UNSPECIFIED(0),
	HTTP_ENABLED(1),
	HTTP_DISABLED(2),
	UNRECOGNIZED(-1),
	;

	public static final int HTTP_STATE_UNSPECIFIED_VALUE = 0;
	public static final int HTTP_ENABLED_VALUE = 1;
	public static final int HTTP_DISABLED_VALUE = 2;

	public final int getNumber() {
		if (this == UNRECOGNIZED) {
			throw new java.lang.IllegalArgumentException(
					"Can't get the number of an unknown enum value.");
		}
		return value;
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static HttpState valueOf(int value) {
		return forNumber(value);
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static HttpState forNumber(int value) {
		switch (value) {
			case 0:
				return HTTP_STATE_UNSPECIFIED;
			case 1:
				return HTTP_ENABLED;
			case 2:
				return HTTP_DISABLED;
			default:
				return null;
		}
	}

	public HttpState findValueByNumber(int number) {
		return HttpState.forNumber(number);
	}

	private final int value;

	private HttpState(int value) {
		this.value = value;
	}

}

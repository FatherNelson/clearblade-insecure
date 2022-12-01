package com.clearblade.cloud.iot.v1.devicetypes;

public enum GatewayType {

	GATEWAY_TYPE_UNSPECIFIED(0),
	GATEWAY(1),
	NON_GATEWAY(2),
	UNRECOGNIZED(-1),
	;

	public static final int GATEWAY_TYPE_UNSPECIFIED_VALUE = 0;
	public static final int GATEWAY_VALUE = 1;
	public static final int NON_GATEWAY_VALUE = 2;

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
	public static GatewayType valueOf(int value) {
		return forNumber(value);
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static GatewayType forNumber(int value) {
		switch (value) {
			case 0:
				return GATEWAY_TYPE_UNSPECIFIED;
			case 1:
				return GATEWAY;
			case 2:
				return NON_GATEWAY;
			default:
				return null;
		}
	}

	public GatewayType findValueByNumber(int number) {
		return GatewayType.forNumber(number);
	}

	private final int value;

	private GatewayType(int value) {
		this.value = value;
	}

}

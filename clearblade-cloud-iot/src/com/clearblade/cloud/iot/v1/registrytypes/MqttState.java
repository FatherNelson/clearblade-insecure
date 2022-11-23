package com.clearblade.cloud.iot.v1.registrytypes;

public enum MqttState {
	MQTT_STATE_UNSPECIFIED(0),
	MQTT_ENABLED(1),
	MQTT_DISABLED(2),
	UNRECOGNIZED(-1),
	;

	public static final int MQTT_STATE_UNSPECIFIED_VALUE = 0;
	public static final int MQTT_ENABLED_VALUE = 1;
	public static final int MQTT_DISABLED_VALUE = 2;

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
	public static MqttState valueOf(int value) {
		return forNumber(value);
	}

	/**
	 * @param value The numeric wire value of the corresponding enum entry.
	 * @return The enum associated with the given numeric wire value.
	 */
	public static MqttState forNumber(int value) {
		switch (value) {
			case 0:
				return MQTT_STATE_UNSPECIFIED;
			case 1:
				return MQTT_ENABLED;
			case 2:
				return MQTT_DISABLED;
			default:
				return null;
		}
	}

	public MqttState findValueByNumber(int number) {
		return MqttState.forNumber(number);
	}

	private final int value;

	private MqttState(int value) {
		this.value = value;
	}

}

package com.clearblade.cloud.iot.v1.utils;

public class Utils {

	/**
	 * Check if env variable set as Binary Data enabled.
	 * 
	 * @return
	 */
	public static boolean isBinary() {
		String isBinary = System.getenv(Constants.BINARYDATA);
		if (isBinary != null && isBinary.equalsIgnoreCase("true")) {
			return true;
		} else
			return false;
	}

	public static boolean isEmpty(Object text) {
		int strLength;
		if (text == null)
			return true;
		if (text == null || (strLength = text.toString().length()) == 0)
			return true;
		for (int i = 0; i < strLength; i++)
			if (!Character.isWhitespace(text.toString().charAt(i)))
				return false;
		return false;
	}

}

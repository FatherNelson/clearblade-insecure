package com.clearblade.cloud.iot.v1.exception;

public class ApplicationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String defaultMessage = "RuntimeException";

	public ApplicationException() {
		super(defaultMessage);
	}

	public ApplicationException(String errorMessage) {
		super(errorMessage);
	}

	public ApplicationException(Throwable throwable) {
		super(throwable);
	}

	public ApplicationException(String message, Throwable throwable) {
		// super(message, throwable);
		super(message, throwable, true, true);
	}
}

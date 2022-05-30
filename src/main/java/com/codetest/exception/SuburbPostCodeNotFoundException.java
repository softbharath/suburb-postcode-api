package com.codetest.exception;

public class SuburbPostCodeNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = -3501388735348562969L;

	public SuburbPostCodeNotFoundException() {
		super();
	}

	public SuburbPostCodeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SuburbPostCodeNotFoundException(String message) {
		super(message);
	}

	public SuburbPostCodeNotFoundException(Throwable cause) {
		super(cause);
	}
}
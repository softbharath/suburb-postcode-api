package com.codetest.exception;

public class SuburbPostCodeAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 6650058503201529135L;
    
	public SuburbPostCodeAlreadyExistsException() {
		super();
	}

	public SuburbPostCodeAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public SuburbPostCodeAlreadyExistsException(String message) {
		super(message);
	}

	public SuburbPostCodeAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
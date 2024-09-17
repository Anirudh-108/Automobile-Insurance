package com.automobile.exception;

public class CannotClaimPolicyException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public CannotClaimPolicyException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
package com.automobile.exception;

public class PolicyNotClaimedException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public PolicyNotClaimedException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
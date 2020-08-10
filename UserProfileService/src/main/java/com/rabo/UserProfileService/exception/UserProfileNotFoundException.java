package com.rabo.UserProfileService.exception;

public class UserProfileNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserProfileNotFoundException(String message) {
		super(message);
	}
}

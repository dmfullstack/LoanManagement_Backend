package com.rabo.LoanService.exception;

public class LoanAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoanAlreadyExistsException(String message) {
		super(message);
	}
}

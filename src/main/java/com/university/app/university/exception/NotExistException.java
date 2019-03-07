package com.university.app.university.exception;

public class NotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotExistException(String error) {
		super(error);
	}

}

package com.cforum.exception;

public class BadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3146198233502495083L;

	public BadRequestException(String message) {
		super(message);
	}
}

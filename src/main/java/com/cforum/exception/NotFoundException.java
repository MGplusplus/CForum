package com.cforum.exception;

public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 507141760797723812L;

	public NotFoundException(String message)
	{
		super(message);
	}
}

package com.cforum.exception;

public class UnAuthorizeRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6662418788097797649L;
	
	public UnAuthorizeRequestException (String message)
	{
		super(message);
	}

}

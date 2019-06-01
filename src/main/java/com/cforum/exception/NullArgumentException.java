package com.cforum.exception;

public class NullArgumentException extends RuntimeException{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -3489372626549320425L;

	public NullArgumentException(String message)
	{
		super(message);
	}
}

package com.descarteaqui.state.exceptions;

public class NoPermissionException extends NullPointerException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 675021720800886400L;

	public NoPermissionException(String message) {
	     super(message);
	}
}
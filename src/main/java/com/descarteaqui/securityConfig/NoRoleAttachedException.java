package com.descarteaqui.securityConfig;

public class NoRoleAttachedException extends NullPointerException{
	
	private static final long serialVersionUID = 664335224103715628L;

	public NoRoleAttachedException(String message) {
	     super(message);
	}
}

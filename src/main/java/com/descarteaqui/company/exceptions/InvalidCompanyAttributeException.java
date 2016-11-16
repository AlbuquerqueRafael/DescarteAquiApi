package com.descarteaqui.company.exceptions;

public class InvalidCompanyAttributeException extends IllegalArgumentException {

	private static final long serialVersionUID = 7370589439320248254L;

	public InvalidCompanyAttributeException(String message) {
	     super(message);
	}
}

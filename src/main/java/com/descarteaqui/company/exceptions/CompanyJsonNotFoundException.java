package com.descarteaqui.company.exceptions;

public class CompanyJsonNotFoundException extends NullPointerException{
	private static final long serialVersionUID = -2378608358166304438L;

	public CompanyJsonNotFoundException(String message) {
	     super(message);
	}
}

package com.descarteaqui.general;

public class InvalidDataException extends NullPointerException{
	
	private static final long serialVersionUID = 664335224103715628L;

	public InvalidDataException(String message) {
	     super(message);
		 System.out.println(message);
	}

}

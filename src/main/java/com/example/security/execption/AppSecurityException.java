package com.example.security.execption;

public class AppSecurityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppSecurityException(String msg) {
		super(msg);
	}
}

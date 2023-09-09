package com.dnb.jdbcdemo.demo.exceptions;

public class InvalidUUIDException extends Exception {
	
	public InvalidUUIDException(String msg) {
		super(msg);
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + getMessage();
	}
}

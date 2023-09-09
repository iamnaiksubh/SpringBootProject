package com.dnb.jdbcdemo.demo.exceptions;

public class InvalidPanException extends Exception {
	
	public InvalidPanException(String msg) {
		super(msg);
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + getMessage();
	}
}

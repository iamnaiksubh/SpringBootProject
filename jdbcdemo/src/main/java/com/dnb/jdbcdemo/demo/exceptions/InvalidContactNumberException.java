package com.dnb.jdbcdemo.demo.exceptions;

public class InvalidContactNumberException extends Exception{
	
	public InvalidContactNumberException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + getMessage();
	}
}

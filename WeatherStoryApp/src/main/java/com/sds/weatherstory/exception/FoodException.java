package com.sds.weatherstory.exception;

public class FoodException extends RuntimeException{
	
	public FoodException(String msg) {
		super(msg);
	}
	
	public FoodException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public FoodException(Throwable e) {
		super(e);
	}
}

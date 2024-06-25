package com.sds.weatherstory.exception;

public class LikeyException extends RuntimeException{
	
	public LikeyException(String msg) {
		super(msg);
	}
	
	public LikeyException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public LikeyException(Throwable e) {
		super(e);
	}
}

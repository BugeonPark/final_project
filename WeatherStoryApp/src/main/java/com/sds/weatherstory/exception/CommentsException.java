package com.sds.weatherstory.exception;

public class CommentsException extends RuntimeException{
	
	public CommentsException(String msg) {
		super(msg);
	}
	
	public CommentsException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public CommentsException(Throwable e) {
		super(e);
	}
}

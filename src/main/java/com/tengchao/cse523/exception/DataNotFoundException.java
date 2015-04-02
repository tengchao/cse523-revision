package com.tengchao.cse523.exception;

public class DataNotFoundException extends RuntimeException {

	public DataNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DataNotFoundException(String msg) {
		super(msg);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

}

package com.tengchao.cse523.exception;

public class JsonException extends RuntimeException {

	public JsonException(String message) {
		super(message);
	}

	public JsonException(Throwable cause) {
		super(cause);
	}

	public JsonException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

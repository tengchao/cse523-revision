package com.tengchao.cse523.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tengchao.cse523.dto.ErrorResponse;

/**
 *
 * Performs the exception handling and offers them globally. The exceptions
 * below could be raised by any controller and they would be handled here, if
 * not handled in the controller already.
 *
 */

@ControllerAdvice
public class GenericExceptionHandler {

	/**
	 * Converts predefined exception to an Http status code
	 * {@link HttpStatus#NOT_FOUND}
	 * 
	 * @return {@link ErrorResponse}
	 */
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorResponse dataNotFound(DataNotFoundException e) {
		return new ErrorResponse(e.getMessage());
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse badRequest(BadRequestException e) {
		return new ErrorResponse(e.getMessage());
	}

	@ExceptionHandler(JsonException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse jsonError(JsonException e) {
		return new ErrorResponse(e.getMessage());
	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse ioError(IOException e) {
		return new ErrorResponse(e.getMessage());
	}

}

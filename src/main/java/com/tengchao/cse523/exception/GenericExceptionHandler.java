package com.tengchao.cse523.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tengchao.cse523.dto.ErrorResponse;

/**
*
*         Performs the exception handling and offers them globally. The
*         exceptions below could be raised by any controller and they would be
*         handled here, if not handled in the controller already.
*
*/

@ControllerAdvice
public class GenericExceptionHandler {

	/**
     * Converts predefined exception to an Http status code
     * {@link HttpStatus#NOT_FOUND}
     *  @return {@link ErrorResponse}
     */
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorResponse dataNotFound(DataNotFoundException e){
		return new ErrorResponse(e.getMessage());
	}
	
	
}

package com.cforum.exception.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cforum.exception.BadRequestException;
import com.cforum.exception.InvalidException;
import com.cforum.exception.NotFoundException;
import com.cforum.exception.NullArgumentException;
import com.cforum.exception.UnAuthorizeRequestException;
import com.cforum.response.ErrorMessage;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request)
	{
		ErrorMessage errorMessage = new ErrorMessage();
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NullPointerException.class})
	public ResponseEntity<Object> handleAnyNullPointerException(NullPointerException ex, WebRequest request)
	{
		ErrorMessage errorMessage = new ErrorMessage();
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {InvalidException.class})
	public ResponseEntity<Object> handleAnyInvalidException(InvalidException ex, WebRequest request)
	{
		ErrorMessage errorMessage = new ErrorMessage(new Date(), 500, ex.getMessage(), "No message", "");
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NullArgumentException.class})
	public ResponseEntity<Object> handleAnyNullPointerException(NullArgumentException ex, WebRequest request)
	{
		ErrorMessage errorMessage = new ErrorMessage(new Date(), 400, ex.getMessage(), "No message", "");
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {BadRequestException.class})
	public ResponseEntity<Object> handleAnyBadRequestException(BadRequestException ex, WebRequest request)
	{
		ErrorMessage errorMessage = new ErrorMessage(new Date(), 400, ex.getMessage(), request.getDescription(false), ex.toString());
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {NotFoundException.class})
	public ResponseEntity<Object> handleAnyNotFoundException(NotFoundException ex, WebRequest request)
	{
		ErrorMessage errorMessage = new ErrorMessage(new Date(), 404, ex.getMessage(), request.getDescription(false), ex.toString());
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {UnAuthorizeRequestException.class})
	public ResponseEntity<Object> handleAnyUnAuthorizeRequestException(UnAuthorizeRequestException ex, WebRequest request)
	{
		ErrorMessage errorMessage = new ErrorMessage(new Date(), 403, ex.getMessage(), request.getDescription(false), ex.toString());
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
									HttpHeaders headers, HttpStatus status,WebRequest request)
	{
		List<String> details = new ArrayList<>();
		for (ObjectError error :ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}

		ErrorMessage errorMessage = new ErrorMessage(new Date(), 400, details.toString(), "No Message", "");
		
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}

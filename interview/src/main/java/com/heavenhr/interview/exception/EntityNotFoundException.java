package com.heavenhr.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3396573001013833493L;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(String s) {
		super(s);
	}

	public EntityNotFoundException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public EntityNotFoundException(Throwable throwable) {
		super(throwable);
	}

}

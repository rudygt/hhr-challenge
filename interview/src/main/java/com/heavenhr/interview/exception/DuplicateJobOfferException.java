package com.heavenhr.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateJobOfferException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4604272710707590624L;

	public DuplicateJobOfferException() {
		super();
	}

	public DuplicateJobOfferException(String s) {
		super(s);
	}

	public DuplicateJobOfferException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public DuplicateJobOfferException(Throwable throwable) {
		super(throwable);
	}

}

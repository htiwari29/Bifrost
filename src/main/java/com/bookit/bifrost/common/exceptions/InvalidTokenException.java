package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.Errors;

public class InvalidTokenException extends BifrostException {

	public InvalidTokenException(Errors errors) {
		super(errors);
	}

	public InvalidTokenException(Exception ex, Errors errors) {
		super(ex, errors);
	}

}

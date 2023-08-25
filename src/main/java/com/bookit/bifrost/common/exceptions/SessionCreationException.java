package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.Errors;

public class SessionCreationException extends BifrostException {

	public SessionCreationException(Errors errors) {
		super(errors);
	}

	public SessionCreationException(Exception ex, Errors errors) {
		super(ex, errors);
	}

}

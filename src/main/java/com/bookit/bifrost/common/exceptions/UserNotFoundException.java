package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.Errors;

public class UserNotFoundException extends BifrostException {

	public UserNotFoundException(Errors errors) {
		super(errors);
	}

	public UserNotFoundException(Exception ex, Errors errors) {
		super(ex, errors);
	}

}

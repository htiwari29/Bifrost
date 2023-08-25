package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.Errors;

public class UserException extends BifrostException {

	public UserException(Errors errors) {
		super(errors);
	}

	public UserException(Exception ex, Errors errors) {

		super(ex, errors);
	}

}

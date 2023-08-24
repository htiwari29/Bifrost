package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.Errors;

public class UserCreationException extends BifrostException {

	public UserCreationException(Errors errors) {
		super(errors);
	}

}

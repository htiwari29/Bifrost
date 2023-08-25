package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.Errors;

public class SessionLogoutException extends BifrostException {

	public SessionLogoutException(Exception ex, Errors errors) {
		super(ex, errors);
	}

	public SessionLogoutException(Errors errors) {
		super(errors);
	}

}

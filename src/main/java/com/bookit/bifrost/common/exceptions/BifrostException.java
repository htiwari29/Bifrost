package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.errors.Errors;

import java.util.Objects;

public class BifrostException extends RuntimeException {

	private final Errors errors;

	public BifrostException(Errors errors) {
		super(errors.getMessage());
		this.errors = assignErrors(errors);
	}

	public BifrostException(Exception ex, Errors errors) {
		super(ex);
		this.errors = assignErrors(errors);
	}

	private Errors assignErrors(Errors errors) {
		return Objects.isNull(errors) ? ErrorFactory.createGenericError() : errors;
	}

	public Errors getErrors() {
		return errors;
	}

}

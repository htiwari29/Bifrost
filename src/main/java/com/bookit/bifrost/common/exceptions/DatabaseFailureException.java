package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.errors.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseFailureException extends BifrostException {

	public static final Logger log = LoggerFactory.getLogger(DatabaseFailureException.class);

	public DatabaseFailureException(Errors errors) {
		super(errors);
	}

	public DatabaseFailureException(Exception ex, Errors errors) {
		super(ex, errors);
	}

	public static DatabaseFailureException whileCreatingUser(Exception ex) {
		log.error("Database failure exception while creating user");
		throw new DatabaseFailureException(ex, ErrorFactory.databaseFailureExceptionWhileCreatingUser());
	}

	public static DatabaseFailureException whileRetrievingUserByUsernameAndTenantId(Exception ex) {
		log.error("Database failure exception while retrieving user by username & tenant id");
		throw new DatabaseFailureException(ex,
				ErrorFactory.databaseFailureExceptionWhileRetrievingUserByUsernameAndTenantId());
	}

	public static DatabaseFailureException whileRetrievingUserByEmail(Exception ex) {
		log.error("Database failure exception while retrieving user by email");
		throw new DatabaseFailureException(ex, ErrorFactory.databaseFailureExceptionWhileRetrievingUserByEmail());
	}

}

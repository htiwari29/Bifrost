package com.bookit.bifrost.common.errors;

import com.bookit.bifrost.common.util.TargetType;

public class ErrorFactory {

	private ErrorFactory() {
	}

	public static Errors createGenericError() {
		return new Errors(TargetType.GENERIC_ERROR.name(), ErrorCodeAndMessages.GENERIC_CODE,
				ErrorCodeAndMessages.GENERIC_MESSAGE);
	}

	public static Errors createInvalidRequestError(TargetType targetType, String message, int code) {
		return new Errors(targetType.name(), code, message);
	}

	public static Errors userExistsWithGivenUsernameAndTenantId() {
		return new Errors(TargetType.USER_CREATION.name(), ErrorCodeAndMessages.USER_EXISTS_WITH_GIVEN_CREDENTIALS,
				ErrorCodeAndMessages.USER_EXISTS_WITH_GIVEN_CREDENTIALS_MSG);
	}

	public static Errors whileRetrievingUserByUsername() {
		return new Errors(TargetType.USER.name(), ErrorCodeAndMessages.ERROR_WHILE_RETRIEVING_USER_BY_USERNAME_CODE,
				ErrorCodeAndMessages.ERROR_WHILE_RETRIEVING_USER_BY_USERNAME);
	}

	public static Errors whileRetrievingUserByUsernameAndTenantId() {
		return new Errors(TargetType.USER.name(), ErrorCodeAndMessages.ERROR_WHILE_RETRIEVING_USER_BY_USERNAME_CODE,
				ErrorCodeAndMessages.ERROR_WHILE_RETRIEVING_USER_BY_USERNAME);
	}

	public static Errors databaseFailureExceptionWhileCreatingUser() {
		return new Errors(TargetType.USER_CREATION.name(), ErrorCodeAndMessages.ERROR_WHEN_CREATING_USER_CODE,
				ErrorCodeAndMessages.ERROR_WHEN_CREATING_USER_MSG);
	}

	public static Errors databaseFailureExceptionWhileRetrievingUserByUsernameAndTenantId() {
		return new Errors(TargetType.USER_RETRIEVAL.name(), ErrorCodeAndMessages.ERROR_WHEN_RETRIEVING_USER_CODE,
				ErrorCodeAndMessages.ERROR_WHEN_RETRIEVING_USER_MSG);
	}

	public static Errors userNotFoundWhileRetrievingUserByUsername() {
		return new Errors(TargetType.USER_RETRIEVAL.name(), ErrorCodeAndMessages.USER_NOT_FOUND_BY_USERNAME_CODE,
				ErrorCodeAndMessages.USER_NOT_FOUND_BY_USERNAME_MSG);
	}

	public static Errors userNotFoundWhileRetrievingUserByUsernameAndTenantId() {
		return new Errors(TargetType.USER_RETRIEVAL.name(),
				ErrorCodeAndMessages.USER_DOES_NOT_EXISTS_WITH_GIVEN_USERNAME_AND_TENANT_ID_CODE,
				ErrorCodeAndMessages.USER_DOES_NOT_EXISTS_WITH_GIVEN_USERNAME_AND_TENANT_ID);
	}

	public static Errors databaseFailureExceptionWhileRetrievingUserByEmail() {
		return new Errors(TargetType.USER_RETRIEVAL.name(),
				ErrorCodeAndMessages.ERROR_WHEN_RETRIEVING_USER_BY_EMAIL_CODE,
				ErrorCodeAndMessages.ERROR_WHEN_RETRIEVING_USER_BY_EMAIL_MSG);
	}

	public static Errors jwtCreationError() {
		return new Errors(TargetType.JWT_CREATION.name(), ErrorCodeAndMessages.JWT_CREATION_ERROR_CODE,
				ErrorCodeAndMessages.JWT_CREATION_ERROR_MSG);
	}

	public static Errors whileSavingSession() {
		return new Errors(TargetType.LOGIN.name(), ErrorCodeAndMessages.ERROR_WHILE_CREATING_SESSION_CODE,
				ErrorCodeAndMessages.ERROR_WHILE_CREATING_SESSION_MSG);
	}

	public static Errors sessionNotFoundBySessionTokenAndUsername() {
		return new Errors(TargetType.LOGOUT.name(), ErrorCodeAndMessages.INVALID_SESSION_TOKEN_OR_USERNAME_CODE,
				ErrorCodeAndMessages.INVALID_SESSION_TOKEN_OR_USERNAME);
	}

	public static Errors exceptionDuringUserLogout() {
		return new Errors(TargetType.LOGOUT.name(), ErrorCodeAndMessages.ERROR_DURING_LOGOUT_CODE,
				ErrorCodeAndMessages.ERROR_DURING_LOGOUT_MSG);
	}

	public static Errors errorWhileAuthenticatingUser() {
		return new Errors(TargetType.LOGIN.name(), ErrorCodeAndMessages.ERROR_WHILE_USER_AUTH_CODE,
				ErrorCodeAndMessages.ERROR_WHILE_USER_AUTHENTICATION);
	}

	public static Errors invalidTokenError() {
		return new Errors(TargetType.INVALID_TOKEN.name(), ErrorCodeAndMessages.INVALID_TOKEN_CODE,
				ErrorCodeAndMessages.INVALID_TOKEN);
	}

}

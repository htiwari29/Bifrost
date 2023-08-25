package com.bookit.bifrost.common.errors;

public class ErrorCodeAndMessages {

	private ErrorCodeAndMessages() {
	}

	public static final String GENERIC_MESSAGE = "Error while executing request";

	public static final int GENERIC_CODE = 1000;

	public static final String INVALID_USER_ID_MESSAGE = "User id is invalid";

	public static final int INVALID_USER_ID_CODE = 1001;

	public static final String USER_EXISTS_WITH_GIVEN_CREDENTIALS_MSG = "User exists with given credentials";

	public static final int USER_EXISTS_WITH_GIVEN_CREDENTIALS = 1002;

	public static final String INVALID_USER_EMAIL_MESSAGE = "User email is invalid";

	public static final int INVALID_USER_EMAIL_CODE = 1003;

	public static final String INVALID_USER_NAME_MESSAGE = "User name is invalid";

	public static final int INVALID_USER_NAME_CODE = 1004;

	public static final String INVALID_USER_PASSWORD_MSG = "User password is invalid";

	public static final int INVALID_USER_PASSWORD_CODE = 1005;

	public static final String ERROR_WHEN_CREATING_USER_MSG = "Database failure while creating user";

	public static final int ERROR_WHEN_CREATING_USER_CODE = 1006;

	public static final String ERROR_WHEN_RETRIEVING_USER_MSG = "Database failure while retrieving user";

	public static final int ERROR_WHEN_RETRIEVING_USER_CODE = 1007;

	public static final String USER_NOT_FOUND_BY_USERNAME_MSG = "User does not exists with given username";

	public static final int USER_NOT_FOUND_BY_USERNAME_CODE = 1008;

	public static final String ERROR_WHEN_RETRIEVING_USER_BY_EMAIL_MSG = "Database failure while retrieving user by email";

	public static final int ERROR_WHEN_RETRIEVING_USER_BY_EMAIL_CODE = 1009;

	public static final String USER_DOES_NOT_EXISTS_WITH_GIVEN_USERNAME_AND_TENANT_ID = "User does not exists with given username and tenantId";

	public static final int USER_DOES_NOT_EXISTS_WITH_GIVEN_USERNAME_AND_TENANT_ID_CODE = 1010;

	public static final String JWT_CREATION_ERROR_MSG = "Error while creating JWT token";

	public static final int JWT_CREATION_ERROR_CODE = 1011;

	public static final String ERROR_WHILE_CREATING_SESSION_MSG = "Error while creating session";

	public static final int ERROR_WHILE_CREATING_SESSION_CODE = 1025;

}

package com.bookit.bifrost.common.errors;


import com.bookit.bifrost.common.util.TargetType;

public class ErrorFactory {

    private ErrorFactory() { }

    public static Errors createGenericError() {
        return new Errors(TargetType.GENERIC_ERROR.name(), ErrorCodeAndMessages.GENERIC_CODE,
                ErrorCodeAndMessages.GENERIC_MESSAGE);
    }

    public static Errors createInvalidRequestError(TargetType targetType, String message, int code) {
        return new Errors(targetType.name(), code, message);
    }

    public static Errors userExistsWithGivenEmailError(){
        return new Errors(TargetType.USER_CREATION.name(), ErrorCodeAndMessages.USER_EXISTS_WITH_GIVEN_EMAIL_CODE,
                ErrorCodeAndMessages.USER_EXISTS_WITH_GIVEN_EMAIL_MESSAGE);
    }

    public static Errors databaseFailureExceptionWhileCreatingUser(){
        return new Errors(TargetType.USER_CREATION.name(), ErrorCodeAndMessages.ERROR_WHEN_CREATING_USER_CODE,
                ErrorCodeAndMessages.ERROR_WHEN_CREATING_USER_MSG);
    }

    public static Errors databaseFailureExceptionWhileRetrievingUserById(){
        return new Errors(TargetType.USER_RETRIEVAL.name(), ErrorCodeAndMessages.ERROR_WHEN_RETRIEVING_USER_BY_ID_CODE,
                ErrorCodeAndMessages.ERROR_WHEN_RETRIEVING_USER_BY_ID_MSG);
    }

    public static Errors userNotFoundWhileRetrievingUserById(){
        return new Errors(TargetType.USER_RETRIEVAL.name(), ErrorCodeAndMessages.USER_NOT_FOUND_BY_ID_CODE,
                ErrorCodeAndMessages.USER_NOT_FOUND_BY_ID_MSG);
    }

    public static Errors userNotFoundWhileRetrievingUserByEmail(){
        return new Errors(TargetType.USER_RETRIEVAL.name(), ErrorCodeAndMessages.USER_NOT_FOUND_BY_EMAIL_CODE,
                ErrorCodeAndMessages.USER_NOT_FOUND_BY_EMAIL_MSG);
    }

    public static Errors databaseFailureExceptionWhileRetrievingUserByEmail(){
        return new Errors(TargetType.USER_RETRIEVAL.name(), ErrorCodeAndMessages.ERROR_WHEN_RETRIEVING_USER_BY_EMAIL_CODE,
                ErrorCodeAndMessages.ERROR_WHEN_RETRIEVING_USER_BY_EMAIL_MSG);
    }

}

package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.errors.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundException extends UserException {

    public static final Logger log = LoggerFactory.getLogger(UserNotFoundException.class);

    public UserNotFoundException(Errors errors) {
        super(errors);
    }

    public static UserNotFoundException whileRetrievingUserById() {
        log.error("User not found while retrieving user by id");
        throw new UserNotFoundException(ErrorFactory.userNotFoundWhileRetrievingUserById());
    }

    public static UserNotFoundException whileRetrievingUserByEmail() {
        throw new UserNotFoundException(ErrorFactory.userNotFoundWhileRetrievingUserByEmail());
    }

}
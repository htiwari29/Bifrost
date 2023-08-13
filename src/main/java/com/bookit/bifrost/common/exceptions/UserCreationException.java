package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.errors.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserCreationException extends UserException {

    public static final Logger log = LoggerFactory.getLogger(UserCreationException.class);

    public UserCreationException(Errors errors) {
        super(errors);
    }

    public UserCreationException(Exception ex, Errors errors) {
        super(ex, errors);
    }

    public static UserCreationException userExistsWithGivenEmail(){
        log.error("User exists with given email");
        return new UserCreationException(ErrorFactory.userExistsWithGivenEmailError());
    }

}

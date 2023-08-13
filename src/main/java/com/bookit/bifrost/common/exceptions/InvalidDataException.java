package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.errors.Errors;
import com.bookit.bifrost.common.util.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidDataException extends BifrostException {

    public static final Logger log = LoggerFactory.getLogger(InvalidDataException.class);

    public InvalidDataException(Exception ex, Errors errors) {
        super(ex, errors);
    }

    public static InvalidDataException invalidDataException(TargetType targetType, String message, int code) {
        log.error("Invalid data in request body/params");
        throw new InvalidDataException(new Exception("Invalid Request"),
                ErrorFactory.createInvalidRequestError(targetType, message, code));
    }

}

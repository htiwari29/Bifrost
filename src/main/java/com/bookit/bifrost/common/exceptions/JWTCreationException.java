package com.bookit.bifrost.common.exceptions;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.errors.Errors;

public class JWTCreationException extends BifrostException {

    public JWTCreationException(Exception ex, Errors errors) {
        super(ex, errors);
    }

    public static JWTCreationException whileCreatingToken(Exception ex){
        throw new JWTCreationException(ex, ErrorFactory.JWTCreationError());
    }

}

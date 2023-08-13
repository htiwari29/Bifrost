package com.bookit.bifrost.common.validators;

import com.bookit.bifrost.common.exceptions.InvalidDataException;
import com.bookit.bifrost.common.util.TargetType;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommonDataValidator {

    protected void handleValidationFailure(TargetType targetType, String message, int code){
        throw InvalidDataException.invalidDataException(targetType, message, code);
    }

    protected boolean isValidInteger(String val){
        return StringUtils.isNumeric(val);
    }

    protected boolean isValidMail(String val){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(val);
        return matcher.matches();
    }

    protected boolean isValidString(String val) {
        return val != null && !val.isEmpty() && !val.equalsIgnoreCase("null");
    }

}

package com.bookit.bifrost.entrypoints.validators;

import com.bookit.bifrost.entrypoints.dtos.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class BifrostDataValidator {
    public void validateRegisterRequest(RegisterRequest registerRequest, String clientId, String clientSecret) {
        return;
    }
}

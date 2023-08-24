package com.bookit.bifrost.entrypoints.validators;

import com.bookit.bifrost.entrypoints.dtos.LoginRequest;
import com.bookit.bifrost.entrypoints.dtos.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class BifrostDataValidator {

	public void validateRegisterRequest(RegisterRequest registerRequest, String clientId, String clientSecret) {
		return;
	}

	public void validateLoginRequest(LoginRequest loginRequest, String clientId, String clientSecret) {
		return;
	}

}

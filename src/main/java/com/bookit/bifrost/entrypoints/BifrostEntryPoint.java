package com.bookit.bifrost.entrypoints;

import com.bookit.bifrost.appservices.BifrostAuthService;
import com.bookit.bifrost.common.exceptions.BifrostException;
import com.bookit.bifrost.entrypoints.dtos.LoginRequest;
import com.bookit.bifrost.entrypoints.dtos.RegisterRequest;
import com.bookit.bifrost.common.responses.FailureResponseComposer;
import com.bookit.bifrost.common.responses.SuccessResponseComposer;
import com.bookit.bifrost.entrypoints.validators.BifrostDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BifrostEntryPoint {

	private static final Logger log = LoggerFactory.getLogger(BifrostEntryPoint.class);

	public static final String CLIENT_SECRET = "clientSecret";

	public static final String CLIENT_ID = "clientId";

	private final BifrostDataValidator bifrostDataValidator;

	private final BifrostAuthService bifrostAuthService;

	public BifrostEntryPoint(BifrostDataValidator bifrostDataValidator, BifrostAuthService bifrostAuthService) {
		this.bifrostDataValidator = bifrostDataValidator;
		this.bifrostAuthService = bifrostAuthService;
	}

	@PostMapping(value = "v1/user/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest,
			@RequestHeader(CLIENT_ID) String clientId, @RequestHeader(CLIENT_SECRET) String clientSecret) {
		try {
			bifrostDataValidator.validateRegisterRequest(registerRequest, clientId, clientSecret);
			return SuccessResponseComposer.composeResponse(bifrostAuthService.register(registerRequest));
		}
		catch (BifrostException ex) {
			log.error("Error in user registration", ex);
			return FailureResponseComposer.composeResponse(ex);
		}
	}

	@PostMapping(value = "v1/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest,
			@RequestHeader(CLIENT_ID) String clientId, @RequestHeader(CLIENT_SECRET) String clientSecret) {
		try {
			bifrostDataValidator.validateLoginRequest(loginRequest, clientId, clientSecret);
			return SuccessResponseComposer.composeResponse(bifrostAuthService.login(loginRequest));
		}
		catch (BifrostException ex) {
			log.error("Error in user login", ex);
			return FailureResponseComposer.composeResponse(ex);
		}
	}

	@PostMapping(value = "v1/user/logout", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> logout(@RequestHeader("sessionToken") String sessionToken,
			@RequestHeader("username") String username) {
		try {
			bifrostDataValidator.validateLogoutRequest(sessionToken, username);
			return SuccessResponseComposer.composeResponse(bifrostAuthService.logout(sessionToken, username));
		}
		catch (BifrostException ex) {
			log.error("Error in user logout", ex);
			return FailureResponseComposer.composeResponse(ex);
		}
	}

}

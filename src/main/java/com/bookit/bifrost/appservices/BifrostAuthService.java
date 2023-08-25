package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.exceptions.BifrostException;
import com.bookit.bifrost.common.exceptions.UserException;
import com.bookit.bifrost.common.exceptions.UserNotFoundException;
import com.bookit.bifrost.common.util.TargetType;
import com.bookit.bifrost.common.util.TenantUserToken;
import com.bookit.bifrost.domain.Token;
import com.bookit.bifrost.domain.User;
import com.bookit.bifrost.entrypoints.dtos.LoginRequest;
import com.bookit.bifrost.entrypoints.dtos.RegisterRequest;
import com.bookit.bifrost.entrypoints.responses.BifrostLoginResponse;
import com.bookit.bifrost.entrypoints.responses.BifrostLogoutResponse;
import com.bookit.bifrost.entrypoints.responses.BifrostRegisterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BifrostAuthService {

	private static final Logger log = LoggerFactory.getLogger(BifrostAuthService.class);

	private final UserRetrievalService userRetrievalService;

	private final UserCreationService userCreationService;

	private final BCryptPasswordEncoder encoder;

	private final AuthenticationManager authenticationManager;

	private final TokenCreationService tokenCreationService;

	private final SessionService sessionService;

	public BifrostAuthService(UserRetrievalService userRetrievalService, UserCreationService userCreationService,
			BCryptPasswordEncoder encoder, AuthenticationManager authenticationManager,
			TokenCreationService tokenCreationService, SessionService sessionService) {
		this.userRetrievalService = userRetrievalService;
		this.userCreationService = userCreationService;
		this.encoder = encoder;
		this.authenticationManager = authenticationManager;
		this.tokenCreationService = tokenCreationService;
		this.sessionService = sessionService;
	}

	public BifrostRegisterResponse register(RegisterRequest registerRequest) {
		try {
			log.debug("Checking if user exists with given credentials");
			userRetrievalService.userByUsernameAndTenantId(registerRequest.getUsername(),
					registerRequest.getTenantId());
			log.error("User exists with given credentials");
			throw new UserException(ErrorFactory.userExistsWithGivenUsernameAndTenantId());
		}
		catch (UserNotFoundException ex) {
			log.debug("Creating user with given credentials");
			User user = new User(registerRequest.getUsername(), encoder.encode(registerRequest.getPassword()),
					registerRequest.getTenantId());
			userCreationService.save(user);
			return new BifrostRegisterResponse(TargetType.USER_REGISTERED.name(), user.getUsername());
		}
	}

	public BifrostLoginResponse login(LoginRequest loginRequest) {
		try {
			authenticateUser(loginRequest);
			Token token = tokenCreationService.tokenDetails(loginRequest.getUsername(), loginRequest.getTenantId());
			sessionService.saveSession(token);
			return new BifrostLoginResponse(token.getRefreshToken(), token.getSessionToken());
		}
		catch (InternalAuthenticationServiceException ex) {
			throw new UserNotFoundException(ErrorFactory.userNotFoundWhileRetrievingUserByUsernameAndTenantId());
		}
	}

	public BifrostLogoutResponse logout(String sessionToken, String username) {
		sessionService.logout(sessionToken, username);
		return new BifrostLogoutResponse(TargetType.USER_LOGGED_OUT.name(), username);
	}

	private void authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new TenantUserToken(
				loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getTenantId()));
		if (!authentication.isAuthenticated())
			throw new BifrostException(ErrorFactory.errorWhileAuthenticatingUser());
	}

}

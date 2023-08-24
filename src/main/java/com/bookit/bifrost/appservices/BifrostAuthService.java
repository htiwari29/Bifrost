package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.exceptions.UserCreationException;
import com.bookit.bifrost.common.exceptions.UserNotFoundException;
import com.bookit.bifrost.common.util.TargetType;
import com.bookit.bifrost.domain.User;
import com.bookit.bifrost.entrypoints.dtos.RegisterRequest;
import com.bookit.bifrost.entrypoints.responses.BifrostRegisterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BifrostAuthService {

	private static final Logger log = LoggerFactory.getLogger(BifrostAuthService.class);

	private final UserRetrievalService userRetrievalService;

	private final UserCreationService userCreationService;

	private final BCryptPasswordEncoder encoder;

	public BifrostAuthService(UserRetrievalService userRetrievalService, UserCreationService userCreationService,
			BCryptPasswordEncoder encoder) {
		this.userRetrievalService = userRetrievalService;
		this.userCreationService = userCreationService;
		this.encoder = encoder;
	}

	public BifrostRegisterResponse register(RegisterRequest registerRequest) {
		try {
			log.debug("Checking if user exists with given credentials");
			userRetrievalService.userByUsernameAndTenantId(registerRequest.getUsername(),
					registerRequest.getTenantId());
			throw new UserCreationException(ErrorFactory.userExistsWithGivenUsernameAndTenantId());
		}
		catch (UserNotFoundException ex) {
			log.debug("Creating user with given credentials");
			User user = new User(registerRequest.getUsername(), encoder.encode(registerRequest.getPassword()),
					registerRequest.getTenantId());
			userCreationService.save(user);
			return new BifrostRegisterResponse(TargetType.USER_CREATED.name(), user.getUsername());
		}
	}

}

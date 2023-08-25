package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.exceptions.BifrostException;
import com.bookit.bifrost.common.exceptions.UserException;
import com.bookit.bifrost.common.exceptions.UserNotFoundException;
import com.bookit.bifrost.domain.User;
import com.bookit.bifrost.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserRetrievalService {

	private final UserRepository userRepository;

	public UserRetrievalService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User userByUsername(String username) {
		User user;
		try {
			user = userRepository.findByUsername(username);
		}
		catch (Exception ex) {
			throw new UserException(ex, ErrorFactory.whileRetrievingUserByUsername());
		}
		if (Objects.isNull(user))
			throw new BifrostException(ErrorFactory.userNotFoundWhileRetrievingUserByUsername());
		return user;
	}

	public User userByUsernameAndTenantId(String username, String tenantId) {
		User user;
		try {
			user = userRepository.findByUsernameAndTenantId(username, tenantId);
		}
		catch (Exception ex) {
			throw new UserException(ex, ErrorFactory.whileRetrievingUserByUsernameAndTenantId());
		}
		if (Objects.isNull(user))
			throw new UserNotFoundException(ErrorFactory.userNotFoundWhileRetrievingUserByUsernameAndTenantId());
		return user;
	}

}

package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.exceptions.DatabaseFailureException;
import com.bookit.bifrost.domain.User;
import com.bookit.bifrost.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCreationService {

	private final UserRepository userRepository;

	public UserCreationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save(User user) {
		try {
			return userRepository.save(user);
		}
		catch (Exception ex) {
			throw new DatabaseFailureException(ErrorFactory.databaseFailureExceptionWhileCreatingUser());
		}
	}

}

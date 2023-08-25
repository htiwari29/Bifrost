package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.exceptions.InvalidTokenException;
import com.bookit.bifrost.common.exceptions.SessionCreationException;
import com.bookit.bifrost.common.exceptions.SessionLogoutException;
import com.bookit.bifrost.domain.Session;
import com.bookit.bifrost.domain.Token;
import com.bookit.bifrost.domain.repositories.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SessionService {

	private static final Logger log = LoggerFactory.getLogger(SessionService.class);

	private final SessionRepository sessionRepository;

	private final JwtService jwtService;

	public SessionService(SessionRepository sessionRepository, JwtService jwtService) {
		this.sessionRepository = sessionRepository;
		this.jwtService = jwtService;
	}

	public void saveSession(Token token) {
		Session session = jwtService.sessionFromToken(token);
		try {
			sessionRepository.save(session);
		}
		catch (Exception ex) {
			log.error("Error while saving session");
			throw new SessionCreationException(ex, ErrorFactory.whileSavingSession());
		}
	}

	public void logout(String sessionToken, String username) {
		Optional<Session> session = sessionRepository.findBySessionTokenAndUsername(sessionToken, username);
		if (session.isPresent()) {
			terminateSession(session.get());
		}
		else {
			log.error("User session not found by username & sessionToken");
			throw new SessionLogoutException(ErrorFactory.sessionNotFoundBySessionTokenAndUsername());
		}
	}

	private void terminateSession(Session session) {
		if (session.isValid()) {
			session.setValid(false);
			session.setLastAccessed(new Date(System.currentTimeMillis()));
			sessionRepository.save(session);
		}
		else {
			log.error("Invalid session token provided");
			throw new InvalidTokenException(ErrorFactory.invalidTokenError());
		}
	}

}

package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.errors.ErrorFactory;
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
		try {
			Optional<Session> optionalSession = sessionRepository.findBySessionTokenAndUsername(sessionToken, username);
			if (optionalSession.isPresent()) {
				Session session = optionalSession.get();
				session.setValid(false);
				session.setLastAccessed(new Date(System.currentTimeMillis()));
				sessionRepository.save(session);
			}
			else {
				log.error("User session not found by username & sessionToken");
				throw new SessionLogoutException(ErrorFactory.sessionNotFoundBySessionTokenAndUsername());
			}
		}
		catch (Exception ex) {
			throw new SessionLogoutException(ex, ErrorFactory.exceptionDuringUserLogout());
		}
	}

}

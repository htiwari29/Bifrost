package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.exceptions.DatabaseFailureException;
import com.bookit.bifrost.common.exceptions.SessionCreationException;
import com.bookit.bifrost.domain.Session;
import com.bookit.bifrost.domain.Token;
import com.bookit.bifrost.domain.repositories.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SessionCreationService {

	private static final Logger log = LoggerFactory.getLogger(SessionCreationService.class);

	private final SessionRepository sessionRepository;

	private final JwtService jwtService;

	public SessionCreationService(SessionRepository sessionRepository, JwtService jwtService) {
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

}

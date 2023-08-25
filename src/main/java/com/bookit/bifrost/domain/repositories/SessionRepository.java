package com.bookit.bifrost.domain.repositories;

import com.bookit.bifrost.domain.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session, String> {

	Optional<Session> findBySessionTokenAndUsername(String sessionToken, String username);

}

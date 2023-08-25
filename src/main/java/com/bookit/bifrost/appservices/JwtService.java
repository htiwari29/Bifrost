package com.bookit.bifrost.appservices;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bookit.bifrost.common.exceptions.InvalidDataException;
import com.bookit.bifrost.common.exceptions.JWTCreationException;
import com.bookit.bifrost.common.util.TargetType;
import com.bookit.bifrost.domain.Session;
import com.bookit.bifrost.domain.Token;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class JwtService {

	// Keep in config file
	private static final String secret = "8127A5678B56789S3456A45S567890F3456J5678945678J456Q567";

	public static final Logger log = LoggerFactory.getLogger(JwtService.class);

	public String generateToken(String subject, Map<String, String> claims, int hours) {
		if (CollectionUtils.isEmpty(claims))
			throw InvalidDataException.invalidDataException(TargetType.EMPTY_JWT_CLAIMS, "Error in token creation",
					1023);

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR_OF_DAY, hours);
			JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);
			claims.forEach(jwtBuilder::withClaim);

			return jwtBuilder.withNotBefore(new Date()).withExpiresAt(calendar.getTime())
					.sign(Algorithm.HMAC256(secret));

		}
		catch (Exception ex) {
			throw JWTCreationException.whileCreatingToken(ex);
		}
	}

	public String userNameFromToken(String token) {
		try {
			DecodedJWT decodedJWT = JWT.decode(token);
			return decodedJWT.getSubject();
		}
		catch (Exception ex) {
			throw InvalidDataException.invalidDataException(TargetType.JWT_CLAIM_PARSE, "Error while decoding token",
					1012);
		}
	}

	public String tenantIdFromToken(String token) {
		try {
			String payload = decodePayload(JWT.decode(token).getPayload());
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(payload);
			return jsonNode.get("tenantId").asText();
		}
		catch (Exception ex) {
			throw InvalidDataException.invalidDataException(TargetType.JWT_CLAIM_PARSE, "Error while decoding token",
					1012);
		}
	}

	public boolean validateToken(String token) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
			verifier.verify(token);
			return true;
		}
		catch (Exception ex) {
			log.error("Invalid token: {}", ex.getMessage());
			return false;
		}
	}

	public boolean isTokenExpired(String token) {
		try {
			DecodedJWT decodedJWT = JWT.decode(token);
			Date expriyDate = decodedJWT.getExpiresAt();
			return expriyDate.before(new Date());
		}
		catch (Exception ex) {
			log.error("Invalid token: {}", ex.getMessage());
			return false;
		}
	}

	public Session sessionFromToken(Token token) {
		try {
			String payload = decodePayload(JWT.decode(token.getSessionToken()).getPayload());
			ObjectMapper objectMapper = new ObjectMapper();
			Session session = objectMapper.readValue(payload, Session.class);
			session.setRefreshToken(token.getRefreshToken());
			session.setSessionToken(token.getSessionToken());
			session.setValid(true);
			session.setLastAccessed(new Date(System.currentTimeMillis()));
			return session;
		}
		catch (Exception ex) {
			log.error("Error while generating session details from token");
			throw InvalidDataException.invalidDataException(TargetType.JWT_CLAIM_PARSE, "Error while decoding token",
					1012);
		}
	}

	private String decodePayload(String payload) {
		byte[] decodedBytes = Base64.getDecoder().decode(payload);
		return new String(decodedBytes);
	}

}

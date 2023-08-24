package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.util.JWTHelper;
import com.bookit.bifrost.domain.Token;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenCreationService {

	private final JWTHelper jwtHelper;

	public TokenCreationService(JWTHelper jwtHelper) {
		this.jwtHelper = jwtHelper;
	}

	public String createSessionToken(String username, Map<String, String> claims) {
		int sessionTokenExpirationTimeInHours = 4;
		return jwtHelper.generateToken(username, claims, sessionTokenExpirationTimeInHours);
	}

	public String createAccessToken(String username, Map<String, String> claims) {
		int accessTokenExpirationTimeInHours = 4;
		return jwtHelper.generateToken(username, claims, accessTokenExpirationTimeInHours);
	}

	public Token tokenDetails(String username, String tenantId) {
		Token token = new Token();
		Map<String, String> claims = generateClaims(username, tenantId);
		token.setAccessToken(createAccessToken(username, claims));
		token.setSessionToken(createSessionToken(username, claims));
		return token;
	}

	private Map<String, String> generateClaims(String username, String tenantId) {
		Map<String, String> claims = new HashMap<>();
		claims.put("username", username);
		claims.put("tenantId", tenantId);
		claims.put("creationDate", (new Date(System.currentTimeMillis())).toString());
		claims.put("jti", UUID.randomUUID().toString());
		return claims;
	}

}

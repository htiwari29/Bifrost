package com.bookit.bifrost.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bookit.bifrost.common.exceptions.InvalidDataException;
import com.bookit.bifrost.common.exceptions.JWTCreationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
public class JWTHelper {

    // Keep in config file
    private static final String secret = "77217A25432A462D4A614E645267556B58703272357538782F413F4428472B4B";

    public static final Logger log = LoggerFactory.getLogger(JWTHelper.class);

    public String generateToken(String subject, Map<String, String> claims, int hours) {
        if (CollectionUtils.isEmpty(claims))
            throw InvalidDataException.invalidDataException(TargetType.EMPTY_JWT_CLAIMS, "", 1);

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, hours);
            JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);
            claims.forEach(jwtBuilder::withClaim);

            return jwtBuilder
                    .withNotBefore(new Date())
                    .withExpiresAt(calendar.getTime())
                    .sign(Algorithm.HMAC256(secret));

        } catch (Exception ex) {
            throw JWTCreationException.whileCreatingToken(ex);
        }
    }

    public String userNameFromToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getSubject();
        } catch (Exception ex){
            throw InvalidDataException.invalidDataException(
                    TargetType.JWT_CLAIM_PARSE, "Error while decoding token", 1012);
        }
    }

    public String tenantIdFromToken(String token) {
        try {
            String payload = decodePayload(JWT.decode(token).getPayload());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(payload);
            return jsonNode.get("tenantId").asText();
        } catch (Exception ex) {
            throw InvalidDataException.invalidDataException(
                    TargetType.JWT_CLAIM_PARSE, "Error while decoding token", 1012);
        }
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            verifier.verify(token);
            return true;
        } catch (Exception ex) {
            log.error("Invalid token: {}", ex.getMessage());
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            Date expriyDate = decodedJWT.getExpiresAt();
            return expriyDate.before(new Date());
        } catch (Exception ex) {
            log.error("Invalid token: {}", ex.getMessage());
            return false;
        }
    }

    private String decodePayload(String token) {
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        return new String(decodedBytes);
    }

}

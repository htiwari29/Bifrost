package com.bookit.bifrost.entrypoints.responses;

public class BifrostLoginResponse extends BifrostResponse {

	private String sessionToken;

	private String refreshToken;

	private String tokenType;

	private String expiryDate;

	public BifrostLoginResponse(String sessionToken, String refreshToken) {
		this.sessionToken = sessionToken;
		this.refreshToken = refreshToken;
		this.tokenType = "BEARER";
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

}

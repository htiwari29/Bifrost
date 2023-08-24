package com.bookit.bifrost.entrypoints.responses;

public class BifrostLoginResponse extends BifrostResponse {

	private String accessToken;

	private String refreshToken;

	private String tokenType;

	private String expiryDate;

	public BifrostLoginResponse(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.tokenType = "BEARER";
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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

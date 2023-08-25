package com.bookit.bifrost.entrypoints.responses;

public class BifrostLogoutResponse implements BifrostResponse {

	private String message;

	private String username;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BifrostLogoutResponse(String message, String username) {
		this.message = message;
		this.username = username;
	}

}

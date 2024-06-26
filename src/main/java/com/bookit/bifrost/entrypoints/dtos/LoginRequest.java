package com.bookit.bifrost.entrypoints.dtos;

public class LoginRequest {

	private String username;

	private String password;

	private String tenantId;

	public LoginRequest(String username, String password, String tenantId) {
		this.username = username;
		this.password = password;
		this.tenantId = tenantId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}

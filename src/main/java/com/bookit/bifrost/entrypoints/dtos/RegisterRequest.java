package com.bookit.bifrost.entrypoints.dtos;

import com.bookit.bifrost.common.exceptions.InvalidDataException;
import com.bookit.bifrost.common.util.TargetType;
import org.apache.commons.lang3.StringUtils;

public class RegisterRequest {

	private String username;

	private String tenantId;

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			throw InvalidDataException.invalidDataException(TargetType.USER_CREATION, "Invalid username", 1020);
		}
		this.username = username;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		if (StringUtils.isEmpty(tenantId)) {
			throw InvalidDataException.invalidDataException(TargetType.USER_CREATION, "Invalid tenantId", 1021);
		}
		this.tenantId = tenantId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (StringUtils.isEmpty(password)) {
			throw InvalidDataException.invalidDataException(TargetType.USER_CREATION, "Invalid password", 1023);
		}
		this.password = password;
	}

}

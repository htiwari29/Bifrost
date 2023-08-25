package com.bookit.bifrost.domain;

import com.bookit.bifrost.common.util.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "sessions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {

	@Id
	private String id;

	@JsonProperty(value = "jti", required = true)
	@NotNull(message = "Jti is mandatory")
	private String jti;

	@JsonProperty(value = "username", required = true)
	@NotNull(message = "Username is mandatory")
	private String username;

	private String sessionToken;

	private String accessToken;

	@JsonProperty(value = "tenantId", required = true)
	private String tenantId;

	@JsonDeserialize(using = DateDeserializer.class)
	@JsonProperty(value = "creationDate", required = true)
	@NotNull(message = "Creation date is mandatory")
	private Date creationDate;

	private boolean valid;

	private Date lastAccessed;

	public Session(String id, String jti, String username, String sessionToken, String accessToken, String tenantId,
			Date creationDate, boolean valid, Date lastAccessed) {
		this.id = id;
		this.jti = jti;
		this.username = username;
		this.sessionToken = sessionToken;
		this.accessToken = accessToken;
		this.tenantId = tenantId;
		this.creationDate = creationDate;
		this.valid = valid;
		this.lastAccessed = lastAccessed;
	}

	public Session() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Date getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(Date lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

}

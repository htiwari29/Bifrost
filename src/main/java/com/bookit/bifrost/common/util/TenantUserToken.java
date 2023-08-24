package com.bookit.bifrost.common.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

public class TenantUserToken extends UsernamePasswordAuthenticationToken {

	private final String tenantId;

	public TenantUserToken(Object principal, Object credentials, String tenantId) {
		super(principal, credentials);
		this.tenantId = tenantId;
	}

	public TenantUserToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,
			String tenantId) {
		super(principal, credentials, authorities);
		this.tenantId = tenantId;
	}

	public String getTenantId() {
		return tenantId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass() || !super.equals(obj))
			return false;
		TenantUserToken tenantUserToken = (TenantUserToken) obj;
		return Objects.equals(tenantId, tenantUserToken.tenantId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), tenantId);
	}

}

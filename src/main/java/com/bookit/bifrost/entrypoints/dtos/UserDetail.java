package com.bookit.bifrost.entrypoints.dtos;

import com.bookit.bifrost.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetail implements UserDetails {

    public static final long serialVersionId = 1L;

    private String id;

    private String username;

    @JsonIgnore
    private String password;

    private String tenantId;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetail(String id, String username, String password, String tenantId, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tenantId = tenantId;
        this.authorities = authorities;
    }

    public static UserDetail build(User user) {
        List<GrantedAuthority> authorityList = null;
        return new UserDetail(user.getId(), user.getUsername(), user.getPassword(), user.getTenantId(), authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, tenantId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserDetail user = (UserDetail) obj;
        return Objects.equals(id, user.id);
    }
}

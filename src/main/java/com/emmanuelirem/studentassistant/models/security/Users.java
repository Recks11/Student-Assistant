package com.emmanuelirem.studentassistant.models.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Document
public class Users implements UserDetails {

    @Id
    private String id = UUID.randomUUID().toString();
    private String username;
    private String password;
    private boolean isExpired = false;
    private boolean isLocked = false;
    private boolean isCredentialNotExpired = false;
    private boolean isEnabled = true;
    private Collection<GrantedAuthority> authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setCredentialNotExpired(boolean credentialNotExpired) {
        isCredentialNotExpired = credentialNotExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = AuthorityUtils.createAuthorityList(authorities);
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
        return isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialNotExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

package com.emmanuelirem.studentassistant.models.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Document
public class Users implements UserDetails {
    private static final long serialVersionUID = 4737142575953558025L;

    @Id
    private String id = UUID.randomUUID().toString();
    private String username;
    private String password;
    private boolean isExpired = false;
    private boolean isLocked = false;
    private boolean isCredentialNotExpired = false;
    private boolean isEnabled = true;
    private Collection<GrantedAuthority> authorities;

    @JsonIgnore
    public Users() {
    }

    @JsonCreator
    Users(@JsonProperty("id") final String id,
         @JsonProperty("username") final String username,
         @JsonProperty("password") final String password) {
        super();
        this.id = requireNonNull(id);
        this.username = requireNonNull(username);
        this.password = requireNonNull(password);
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonIgnore
    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    @JsonIgnore
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @JsonIgnore
    public void setCredentialNotExpired(boolean credentialNotExpired) {
        isCredentialNotExpired = credentialNotExpired;
    }

    @JsonIgnore
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @JsonIgnore
    public void setAuthorities(String[] authorities) {
        this.authorities = AuthorityUtils.createAuthorityList(authorities);
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return isExpired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return isLocked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialNotExpired;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isExpired=" + isExpired +
                ", isLocked=" + isLocked +
                ", isCredentialNotExpired=" + isCredentialNotExpired +
                ", isEnabled=" + isEnabled +
                ", authorities=" + authorities +
                '}';
    }
}

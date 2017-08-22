package com.artronics.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AccountAuthenticationToken extends AbstractAuthenticationToken {
    private final AccountPrincipal principal;
    private Object credentials;

    public AccountAuthenticationToken(AccountPrincipal principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        setAuthenticated(false);

        this.principal = principal;
        this.credentials = credentials;
    }

    public AccountAuthenticationToken(AccountPrincipal principal) {
        this(principal, null, null);
    }

    public AccountAuthenticationToken(AccountPrincipal principal, Object credentials) {
        this(principal, credentials, null);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}

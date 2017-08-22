package com.artronics.security;

import com.artronics.model.User;

public class AccountPrincipal implements java.security.Principal {

    private User user;
    private final String email;
    private final Long accountId;

    public AccountPrincipal(String email, Long accountId) {
        this.email = email;
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long username() {
        return accountId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof AccountPrincipal)) return false;

        AccountPrincipal other = (AccountPrincipal) obj;
        return other.accountId == this.accountId && other.email == this.email;

    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + accountId.hashCode();
        return result;
    }

    @Override
    public String getName() {
        return email + " account_id: " + accountId;
    }

    @Override
    public String toString() {
        return "AccountPrincipal{" +
                "email='" + email + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}

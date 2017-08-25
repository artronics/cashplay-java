package com.artronics.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ApiController {
    protected void checkAuth(Authentication auth, Long accountId, Long userId) {
        if (auth.getPrincipal() != accountId) {
            throw new AccessDeniedException("access denied.");
        }
        if (auth.getDetails() != userId) {
            throw new AccessDeniedException("You are not the authenticated User");
        }
    }
}

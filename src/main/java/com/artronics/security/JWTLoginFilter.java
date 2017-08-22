package com.artronics.security;

import com.artronics.model.User;
import com.artronics.repository.UserRepository;
import com.artronics.service.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private UserRepository userRepository;

    public JWTLoginFilter(String url, AuthenticationManager authManager, UserRepository userRepository) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        AccountCredentials creds = new ObjectMapper()
                .readValue(req.getInputStream(), AccountCredentials.class);

        User user = userRepository.findByEmail(creds.getEmail());
        AccountPrincipal principal = new AccountPrincipal(user.getEmail(), user.getAccount().getId());

        Authentication authentication = getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        principal,
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );
        return authentication;
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        AccountPrincipal principal = (AccountPrincipal) auth.getPrincipal();
        User user = userRepository.findByEmail(principal.getEmail());
        TokenAuthenticationService.addAuthentication(res, user);
    }
}

package com.artronics.security;

import com.artronics.model.Account;
import com.artronics.model.User;
import com.artronics.repository.AccountRepository;
import com.artronics.repository.UserRepository;
import com.artronics.service.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public JWTLoginFilter(String url, AuthenticationManager authManager, UserRepository userRepository, AccountRepository accountRepository) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        AccountCredentials creds = new ObjectMapper()
                .readValue(req.getInputStream(), AccountCredentials.class);

        User user = userRepository.findByEmail(creds.getEmail());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getAccount().getId(),
                creds.getPassword(),
                Collections.emptyList()
        );
        token.setDetails(creds.getEmail());

        Authentication authentication = getAuthenticationManager().authenticate(token);

        return authentication;
    }

    @Transactional
    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        addUserAndAccountToRes(res, auth);

        TokenAuthenticationService.addAuthentication(res, (Long) auth.getPrincipal());
    }

    private void addUserAndAccountToRes(HttpServletResponse res, Authentication auth) throws IOException {
        Account account = accountRepository.findOne((Long) auth.getPrincipal());

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        return
                                fieldAttributes.getName() == "password" ||
                                        fieldAttributes.getName() == "customers" ||
                                        fieldAttributes.getName() == "account";
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .serializeNulls()
                .create();

        res.getWriter().write(gson.toJson(account));
    }
}

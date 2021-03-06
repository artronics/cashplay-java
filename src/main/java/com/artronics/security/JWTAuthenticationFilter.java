package com.artronics.security;

import com.artronics.repository.UserRepository;
import com.artronics.service.TokenAuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends GenericFilterBean {
    private UserRepository userRepository;

    public JWTAuthenticationFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        Long accountId = TokenAuthenticationService
                .getAccountId((HttpServletRequest) request);

        Long userId = TokenAuthenticationService.getUserId((HttpServletRequest) request);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                accountId,
                null,
                Collections.emptyList()
        );
        token.setDetails(userId);

        Authentication authentication = token;

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}

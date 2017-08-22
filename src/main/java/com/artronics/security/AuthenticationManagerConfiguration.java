package com.artronics.security;

import com.artronics.model.User;
import com.artronics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("myAuthenticationManager")
public class AuthenticationManagerConfiguration implements AuthenticationManager {
    @Autowired
    private UserRepository userRepository;

    // This is needed for SpEL to access principle in Query Methods
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AccountPrincipal principal = (AccountPrincipal) authentication.getPrincipal();
        String email = principal.getEmail();
        String password = authentication.getCredentials() + "";
        return auth(email, password);
    }

    private Authentication auth(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new BadCredentialsException("1000");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("1000");
        }

        AccountPrincipal principal = new AccountPrincipal(email, user.getAccount().getId());

        return new UsernamePasswordAuthenticationToken(principal, password);
    }
}

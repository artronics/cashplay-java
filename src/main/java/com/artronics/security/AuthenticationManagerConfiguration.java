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
        Long userId = (Long) authentication.getDetails();
        String password = authentication.getCredentials() + "";
        return auth(userId, password, authentication.getPrincipal());
    }

    private Authentication auth(Long userId, String password, Object pricipal) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new BadCredentialsException("1000");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("1000");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(pricipal, password);
        token.setDetails(userId);
        return token;
    }
}

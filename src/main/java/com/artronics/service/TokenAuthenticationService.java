package com.artronics.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, Object accountId) {
        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("account", user.getAccount().getName());

        String JWT = Jwts.builder()
                .setSubject(accountId.toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
//                .setClaims(claims)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static String getUserEmail(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user;
        }
        return null;
    }
}

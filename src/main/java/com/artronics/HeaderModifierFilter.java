package com.artronics;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO add this filter. I tried but it does not work
//@Component
public class HeaderModifierFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("access-control-expose-headers", "authorization");
        System.out.println("kir t;ush");
        filterChain.doFilter(request, response);
    }
}

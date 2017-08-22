package com.artronics;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// instead use filter See WebSecurityConfig
//@ControllerAdvice
public class HeaderModifierAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(final MethodParameter returnType, final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(final Object body,
                                  final MethodParameter returnType,
                                  final MediaType selectedContentType,
                                  final Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  final ServerHttpRequest request,
                                  final ServerHttpResponse response) {

        // Inorder to expose all headers in front end we need it
        System.out.println("kir tush beshe");
        response.getHeaders().add("access-control-expose-headers", "authorization");
        return body;
    }
}

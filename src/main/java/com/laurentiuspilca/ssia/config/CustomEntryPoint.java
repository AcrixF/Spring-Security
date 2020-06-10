package com.laurentiuspilca.ssia.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException auth) throws IOException, ServletException {
        response.addHeader("message", "The user has tried to access illegally");
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}

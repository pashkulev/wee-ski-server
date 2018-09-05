package com.vankata.weeski.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        logger.error("Responding with unauthorized exception. Message - {}", e.getMessage());
        String responseMessage = "Sorry, You're not authorized to access this resource.";
        if (e instanceof DisabledException) {
            responseMessage = "Your account is blocked!";
        } else if (e instanceof BadCredentialsException) {
            responseMessage = "Invalid username or password!";
        }

        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, responseMessage);
    }
}

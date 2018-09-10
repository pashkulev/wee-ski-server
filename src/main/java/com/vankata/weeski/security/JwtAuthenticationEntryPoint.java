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

    private static final String UNAUTHORIZED_MESSAGE = "Sorry, You're not authorized to access this resource.";
    private static final String BLOCKED_ACCOUNT_MESSAGE = "Your account is blocked!";
    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid email or password!";
    private static final String LOGGING_TEMPLATE_MESSAGE = "Responding with unauthorized exception. Message - {}";

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        logger.error(LOGGING_TEMPLATE_MESSAGE, e.getMessage());
        String responseMessage = UNAUTHORIZED_MESSAGE;
        if (e instanceof DisabledException) {
            responseMessage = BLOCKED_ACCOUNT_MESSAGE;
        } else if (e instanceof BadCredentialsException) {
            responseMessage = INVALID_CREDENTIALS_MESSAGE;
        }

        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, responseMessage);
    }
}

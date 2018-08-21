package com.vankata.weeski.interceptors;

import com.vankata.weeski.domain.log.Log;
import com.vankata.weeski.domain.log.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final String GET_METHOD = "GET";

    private final LogRepository logRepository;

    @Autowired
    public LoggingInterceptor(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        String requestMethod = request.getMethod();
        if (!requestMethod.equalsIgnoreCase(GET_METHOD) || response.getStatus() != 200) {
            String uri = request.getRequestURI();
            Integer status = response.getStatus();
            String identity = "anonymous";

            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                identity = principal.getName();
            }

            Log log = new Log(requestMethod, uri, status, identity);
            this.logRepository.save(log);
        }
    }
}
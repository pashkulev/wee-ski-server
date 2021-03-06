package com.vankata.weeski.interceptors;

import com.vankata.weeski.service.BlockedEmailService;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.service.UserService;
import com.vankata.weeski.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserDeletedInterceptor implements HandlerInterceptor {

    private final UserService userService;
    private final BlockedEmailService blockedEmailService;
    private final FileService fileService;

    @Autowired
    public UserDeletedInterceptor(UserService userService,
                                  BlockedEmailService blockedEmailService,
                                  FileService fileService) {
        this.userService = userService;
        this.blockedEmailService = blockedEmailService;
        this.fileService = fileService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("DELETE")) {
            String requestUrl = request.getRequestURI();
            String id = requestUrl.substring(requestUrl.lastIndexOf("/") + 1);
            User user = this.userService.findById(id);

            this.blockedEmailService.addBlockedEmail(user.getEmail());
            this.fileService.deleteFile("users\\profilePictures\\" + user.getProfilePicture());
        }

        return true;
    }
}
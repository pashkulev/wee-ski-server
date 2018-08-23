package com.vankata.weeski.interceptors;

import com.vankata.weeski.domain.course.models.CourseServiceModel;
import com.vankata.weeski.domain.course.service.CourseService;
import com.vankata.weeski.util.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CourseDeletedInterceptor implements HandlerInterceptor {

    private final CourseService courseService;
    private final FileService fileService;

    @Autowired
    public CourseDeletedInterceptor(CourseService courseService,
                                    FileService fileService) {
        this.courseService = courseService;
        this.fileService = fileService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("DELETE")) {
            String requestUrl = request.getRequestURI();
            String id = requestUrl.substring(requestUrl.lastIndexOf("/") + 1);
            CourseServiceModel course = this.courseService.findById(id);
            this.fileService.deleteFile("courses\\" + course.getImage());
        }

        return true;
    }
}
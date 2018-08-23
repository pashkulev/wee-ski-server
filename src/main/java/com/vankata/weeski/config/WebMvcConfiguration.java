package com.vankata.weeski.config;

import com.vankata.weeski.interceptors.CourseDeletedInterceptor;
import com.vankata.weeski.interceptors.LoggingInterceptor;
import com.vankata.weeski.interceptors.UserDeletedInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private static final long MAX_AGE_SECS = 3600;

    private final LoggingInterceptor loggingInterceptor;
    private final UserDeletedInterceptor userDeletedInterceptor;
    private final CourseDeletedInterceptor courseDeletedInterceptor;

    @Autowired
    public WebMvcConfiguration(LoggingInterceptor loggingInterceptor,
                               UserDeletedInterceptor userDeletedInterceptor,
                               CourseDeletedInterceptor courseDeletedInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
        this.userDeletedInterceptor = userDeletedInterceptor;
        this.courseDeletedInterceptor = courseDeletedInterceptor;
    }

    @Bean
    public MappedInterceptor myLoggingInterceptor() {
        return new MappedInterceptor(new String[]{"/api/**"}, this.loggingInterceptor);
    }

    @Bean
    public MappedInterceptor myUserDeletedInterceptor() {
        return new MappedInterceptor(new String[]{"/api/users/*"}, this.userDeletedInterceptor);
    }

    @Bean
    public MappedInterceptor myCourseDeletedInterceptor() {
        return new MappedInterceptor(new String[]{"/api/courses/*"}, this.courseDeletedInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.loggingInterceptor)
//                .addPathPatterns("/api/**");
//    }
}

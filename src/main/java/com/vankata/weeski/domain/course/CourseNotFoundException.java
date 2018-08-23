package com.vankata.weeski.domain.course;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Course not found!")
public class CourseNotFoundException extends RuntimeException {
}

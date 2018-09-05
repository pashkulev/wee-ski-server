package com.vankata.weeski.service;

import com.vankata.weeski.domain.course.enums.SkiDiscipline;
import com.vankata.weeski.domain.course.model.CourseCreateModel;
import com.vankata.weeski.domain.course.model.CourseServiceModel;
import com.vankata.weeski.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface CourseService {

    CourseServiceModel findById(String id);

    List<CourseServiceModel> findAll();

    List<CourseServiceModel> getActiveCourses();

    List<CourseServiceModel> findByDiscipline(SkiDiscipline discipline);

    List<CourseServiceModel> getUpcomingThreeCourses();

    ApiResponse createCourse(CourseCreateModel courseCreateModel, MultipartFile imageFile);

    ApiResponse updateCourse(CourseCreateModel courseCreateModel, String id, MultipartFile imageFile);

    ApiResponse enrollParticipant(String courseId, Principal principal);
}

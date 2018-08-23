package com.vankata.weeski.domain.course.service;

import com.vankata.weeski.domain.course.enums.SkiDiscipline;
import com.vankata.weeski.domain.course.models.CourseCreateModel;
import com.vankata.weeski.domain.course.models.CourseServiceModel;
import com.vankata.weeski.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {

    CourseServiceModel findById(String id);

    List<CourseServiceModel> findAll();

    List<CourseServiceModel> getActiveCourses();

    List<CourseServiceModel> findByDiscipline(SkiDiscipline discipline);

    List<CourseServiceModel> getUpcomingFiveCourses();

    ApiResponse createCourse(CourseCreateModel courseCreateModel, MultipartFile imageFile);

    ApiResponse updateCourse(CourseCreateModel courseCreateModel, String id, MultipartFile imageFile);
}

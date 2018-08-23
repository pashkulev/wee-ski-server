package com.vankata.weeski.controller;

import com.vankata.weeski.domain.course.models.CourseCreateModel;
import com.vankata.weeski.domain.course.models.CourseServiceModel;
import com.vankata.weeski.domain.course.service.CourseService;
import com.vankata.weeski.exception.ValidationException;
import com.vankata.weeski.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCourse(
            @Valid @ModelAttribute CourseCreateModel course,
            BindingResult bindingResult,
            @RequestParam MultipartFile imageFile) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        ApiResponse response = this.courseService.createCourse(course, imageFile);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<ApiResponse> updateCourse(
            @Valid @ModelAttribute CourseCreateModel course,
            BindingResult bindingResult,
            @PathVariable String id,
            @RequestParam(required = false) MultipartFile imageFile) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        ApiResponse response = this.courseService.updateCourse(course, id, imageFile);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.CREATED);
    }

//    @GetMapping
//    public List<CourseServiceModel> allCourses(@RequestParam SkiDiscipline discipline) {
//        return this.courseService.findAll();
//    }

    @GetMapping("/active")
    public List<CourseServiceModel> activeCourses() {
        return this.courseService.getActiveCourses();
    }

    @GetMapping("/recent")
    public List<CourseServiceModel> upcomingFiveCourses() {
        return this.courseService.getUpcomingFiveCourses();
    }
}

package com.vankata.weeski.controller;

import com.vankata.weeski.domain.course.enums.SkiDiscipline;
import com.vankata.weeski.domain.course.model.CourseCreateModel;
import com.vankata.weeski.domain.course.model.CourseServiceModel;
import com.vankata.weeski.service.CourseService;
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
import java.security.Principal;
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
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public List<CourseServiceModel> allCourses(@RequestParam SkiDiscipline discipline) {
        return this.courseService.findByDiscipline(discipline);
    }

    @GetMapping("/active")
    public List<CourseServiceModel> activeCourses() {
        return this.courseService.getActiveCourses();
    }

    @GetMapping("/upcoming")
    public List<CourseServiceModel> upcomingThreeCourses() {
        return this.courseService.getUpcomingThreeCourses();
    }

    @PatchMapping("/{courseId}/enroll")
    public ResponseEntity<ApiResponse> enroll(@PathVariable String courseId, Principal principal) {
        ApiResponse response = this.courseService.enrollParticipant(courseId, principal);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}

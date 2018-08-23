package com.vankata.weeski.domain.course.service;

import com.vankata.weeski.domain.course.enums.SkiDiscipline;
import com.vankata.weeski.domain.course.models.Course;
import com.vankata.weeski.domain.course.models.CourseCreateModel;
import com.vankata.weeski.domain.course.models.CourseServiceModel;
import com.vankata.weeski.domain.course.repository.CourseRepository;
import com.vankata.weeski.exception.ResourceNotFoundException;
import com.vankata.weeski.payload.ApiResponse;
import com.vankata.weeski.util.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private static final String COURSE_IMAGES_FOLDER = "courses\\";

    private final CourseRepository courseRepository;
    private final FileService fileService;
    private final ModelMapper modelMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, FileService fileService, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.fileService = fileService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse createCourse(CourseCreateModel courseCreateModel, MultipartFile imageFile) {
        String fileName = this.fileService.uploadFile(imageFile, COURSE_IMAGES_FOLDER);
        courseCreateModel.setImage(fileName);

        Course course = this.modelMapper.map(courseCreateModel, Course.class);
        this.courseRepository.save(course);

        return new ApiResponse(true, "The Course was created successfully!");
    }

    @Override
    public ApiResponse updateCourse(CourseCreateModel courseCreateModel, String id, MultipartFile imageFile) {
        Optional<Course> optionalCourse = this.courseRepository.findById(id);
        if (!optionalCourse.isPresent()) {
           throw new ResourceNotFoundException("Course", "id", id);
        }

        Course course = optionalCourse.get();

        if (imageFile != null) {
            this.fileService.deleteFile(COURSE_IMAGES_FOLDER + course.getImage());
            String fileName = this.fileService.uploadFile(imageFile, COURSE_IMAGES_FOLDER);
            courseCreateModel.setImage(fileName);
        } else {
            courseCreateModel.setImage(course.getImage());
        }

        course = this.modelMapper.map(courseCreateModel, Course.class);
        course.setId(id);
        this.courseRepository.save(course);

        return new ApiResponse(true, "Course updated successfully!");
    }

    @Override
    public CourseServiceModel findById(String id) {
        Optional<Course> optionalCourse = this.courseRepository.findById(id);
        if (!optionalCourse.isPresent()) {
            throw new ResourceNotFoundException("Course", "id", id);
        }

        return this.modelMapper.map(optionalCourse.get(), CourseServiceModel.class);
    }

    @Override
    public List<CourseServiceModel> findAll() {
        List<Course> courses = this.courseRepository.findAll();
        return this.mapCoursesToViewModels(courses);
    }

    @Override
    public List<CourseServiceModel> getActiveCourses() {
        List<Course> courses = this.courseRepository.findByStartDateAfterOrderByStartDate(LocalDate.now());
        return this.mapCoursesToViewModels(courses);
    }

    @Override
    public List<CourseServiceModel> findByDiscipline(SkiDiscipline discipline) {
        List<Course> courses =
                this.courseRepository.findByStartDateAfterAndDiscipline(LocalDate.now(),discipline);
        return this.mapCoursesToViewModels(courses);
    }

    @Override
    public List<CourseServiceModel> getUpcomingFiveCourses() {
        List<CourseServiceModel> courseServiceModels = this.getActiveCourses();
        return this.getActiveCourses().subList(0, Math.min(courseServiceModels.size(), 5));
    }

    private List<CourseServiceModel> mapCoursesToViewModels(List<Course> courses) {
        CourseServiceModel[] courseServiceModels = this.modelMapper.map(courses, CourseServiceModel[].class);
        return Arrays.asList(courseServiceModels);
    }
}

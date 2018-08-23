package com.vankata.weeski.domain.course.repository;

import com.vankata.weeski.domain.course.enums.SkiDiscipline;
import com.vankata.weeski.domain.course.models.Course;
import com.vankata.weeski.domain.course.models.CourseViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(excerptProjection = CourseViewModel.class)
public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findByStartDateAfterOrderByStartDate(LocalDate now);

    List<Course> findByStartDateAfterAndDiscipline(LocalDate now, SkiDiscipline discipline);

}

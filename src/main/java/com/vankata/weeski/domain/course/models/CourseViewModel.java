package com.vankata.weeski.domain.course.models;

import com.vankata.weeski.domain.course.enums.CourseLevel;
import com.vankata.weeski.domain.course.enums.SkiDiscipline;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

@Projection(types = Course.class, name = "courseViewModel")
public interface CourseViewModel {

    String getId();

    String getTitle();

    CourseLevel getLevel();

    SkiDiscipline getDiscipline();

    String getDescription();

    String getImage();

    double getPrice();

    LocalDate getStartDate();

    LocalDate getEndDate();
}

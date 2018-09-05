package com.vankata.weeski.domain.course.model;

import com.vankata.weeski.domain.course.enums.CourseLevel;
import com.vankata.weeski.domain.course.enums.SkiDiscipline;
import com.vankata.weeski.domain.user.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.List;

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

    List<User> getParticipants();
}

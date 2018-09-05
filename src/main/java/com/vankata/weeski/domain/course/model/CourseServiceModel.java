package com.vankata.weeski.domain.course.model;

import com.vankata.weeski.domain.course.enums.CourseLevel;
import com.vankata.weeski.domain.course.enums.SkiDiscipline;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseServiceModel {

    private String id;

    private String title;

    private CourseLevel level;

    private SkiDiscipline discipline;

    private String description;

    private double price;

    private String image;

    private LocalDate startDate;

    private LocalDate endDate;
}

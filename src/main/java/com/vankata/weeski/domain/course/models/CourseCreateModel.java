package com.vankata.weeski.domain.course.models;

import com.vankata.weeski.domain.course.enums.CourseLevel;
import com.vankata.weeski.domain.course.enums.SkiDiscipline;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CourseCreateModel {

    @NotBlank
    private String title;

    @NotNull
    private CourseLevel level;

    @NotNull
    private SkiDiscipline discipline;

    @NotBlank
    @Length(min=20)
    private String description;

    @NotNull
    private double price;

    private String image;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}

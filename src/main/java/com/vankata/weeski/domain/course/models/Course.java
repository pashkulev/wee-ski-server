package com.vankata.weeski.domain.course.models;

import com.vankata.weeski.domain.course.enums.CourseLevel;
import com.vankata.weeski.domain.course.enums.SkiDiscipline;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseLevel level;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SkiDiscipline discipline;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String image;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
}

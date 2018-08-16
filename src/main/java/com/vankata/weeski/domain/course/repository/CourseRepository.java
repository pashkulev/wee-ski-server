package com.vankata.weeski.domain.course.repository;

import com.vankata.weeski.domain.course.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}

package com.university.app.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.app.university.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}

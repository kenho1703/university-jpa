package com.university.app.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.app.university.domain.Course;

/**
 * @author Thinh Tat
 *
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

}

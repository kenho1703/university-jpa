package com.university.app.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.app.university.domain.StudentCourse;
import com.university.app.university.domain.StudentCourseId;

/**
 * @author Thinh Tat
 *
 */
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {

}

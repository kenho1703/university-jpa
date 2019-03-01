package com.university.app.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.app.university.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

package com.university.app.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.app.university.domain.University;

public interface UniversityRepository extends JpaRepository<University, Long> {

}

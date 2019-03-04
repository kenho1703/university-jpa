package com.university.app.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.app.university.domain.HalfYearGrade;
import com.university.app.university.domain.HalfYearGradeId;

public interface HalfYearGradeRepository extends JpaRepository<HalfYearGrade, HalfYearGradeId> {

}

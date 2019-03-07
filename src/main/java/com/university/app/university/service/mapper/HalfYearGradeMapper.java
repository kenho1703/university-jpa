package com.university.app.university.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.university.app.university.domain.HalfYearGrade;
import com.university.app.university.service.dto.HalfYearGradeDTO;

@Mapper(componentModel = "spring", uses = {})
public interface HalfYearGradeMapper extends EntityMapper<HalfYearGradeDTO, HalfYearGrade> {
	@Mapping(target = "studentId", source = "pk.studentCourse.pk.student.studentId")
	@Mapping(target = "courseId", source = "pk.studentCourse.pk.course.courseId")
	@Mapping(target = "halfYearGradeId", source = "pk.halfYearGradId")
	@Override
	HalfYearGradeDTO toDto(HalfYearGrade entity);
}
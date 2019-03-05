package com.university.app.university.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.university.app.university.domain.HalfYearGrade;
import com.university.app.university.service.dto.HalfYearGradeDTO;

@Mapper(componentModel = "spring", uses = {})
public interface HalfYearGradeMapper extends EntityMapper<HalfYearGradeDTO, HalfYearGrade> {
	@Mapping(target = "studentId", source = "id.studentCourseId.studentId")
	@Mapping(target = "courseId", source = "id.studentCourseId.courseId")
	@Mapping(target = "halfYearGradeId", source = "id.halfYearGradId")
	@Override
	HalfYearGradeDTO toDto(HalfYearGrade entity);
}
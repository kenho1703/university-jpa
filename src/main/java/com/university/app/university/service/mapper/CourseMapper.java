package com.university.app.university.service.mapper;

import org.mapstruct.Mapper;

import com.university.app.university.domain.Course;
import com.university.app.university.service.dto.CourseDTO;

@Mapper(componentModel = "spring", uses = {})
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {

}
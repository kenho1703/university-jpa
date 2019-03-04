package com.university.app.university.service.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
	private Long studentId;

	private String name;

	private List<CourseDTO> courses = new ArrayList<>();

	private UniversityDTO university;
}

package com.university.app.university.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;

import com.university.app.university.domain.Student;
import com.university.app.university.domain.StudentCourse;
import com.university.app.university.service.dto.CourseDTO;
import com.university.app.university.service.dto.StudentDTO;
import com.university.app.university.service.dto.UniversityDTO;

@Mapper(componentModel = "spring", uses = {})
public abstract class StudentMapper {
	public StudentDTO toDto(Student student) {
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(student.getStudentId());
		dto.setName(student.getName());

		if (student.getUniversity() != null) {
			UniversityDTO universityDTO = new UniversityDTO();
			universityDTO.setId(student.getUniversity().getId());
			universityDTO.setName(student.getUniversity().getName());
			universityDTO.setOrgNo(student.getUniversity().getOrgNo());
			dto.setUniversity(universityDTO);
		}

		if (!student.getStudentCourses().isEmpty()) {
			List<CourseDTO> courseList = new ArrayList<>();
			for (StudentCourse studentCourse : student.getStudentCourses()) {
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(studentCourse.getPk().getCourse().getCourseId());
				courseDTO.setName(studentCourse.getPk().getCourse().getName());
				courseList.add(courseDTO);
			}
			dto.setCourses(courseList);
		}

		return dto;
	}

	public List<StudentDTO> toDto(List<Student> entityList) {
		if (entityList == null) {
			return null;
		}

		List<StudentDTO> list = new ArrayList<StudentDTO>(entityList.size());
		for (Student student : entityList) {
			list.add(toDto(student));
		}

		return list;
	}
}

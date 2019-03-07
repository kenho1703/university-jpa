package com.university.app.university.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.app.university.domain.Course;
import com.university.app.university.domain.Student;
import com.university.app.university.domain.StudentCourse;
import com.university.app.university.domain.University;
import com.university.app.university.repository.CourseRepository;
import com.university.app.university.repository.StudentRepository;
import com.university.app.university.repository.UniversityRepository;
import com.university.app.university.service.StudentService;
import com.university.app.university.service.dto.CreateStudentDTO;
import com.university.app.university.service.dto.StudentDTO;
import com.university.app.university.service.mapper.StudentMapper;

/**
 * @author Thinh Tat
 *
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public StudentDTO save(CreateStudentDTO studentDTO) {
		Optional<University> university = universityRepository.findById(studentDTO.getUniversityId());

		Student student = new Student();
		student.setName(studentDTO.getName());
		if (university.isPresent()) {
			student.setUniversity(university.get());
		}

		if (!studentDTO.getCourseIds().isEmpty()) {
			List<Course> courses = courseRepository.findAllById(studentDTO.getCourseIds());

			for (Course course : courses) {
				StudentCourse studentCourse = new StudentCourse();
				studentCourse.setStudent(student);
				studentCourse.setCourse(course);
			}
		}

		return studentMapper.toDto(studentRepository.save(student));
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudentDTO> findAll() {
		return studentRepository.findAll().stream().map(studentMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public Optional<StudentDTO> findOne(Long id) {
		return studentRepository.findById(id).map(studentMapper::toDto);
	}

	@Override
	public void delete(Long id) {
		studentRepository.deleteById(id);
	}

}

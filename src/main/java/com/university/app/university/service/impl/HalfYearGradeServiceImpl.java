package com.university.app.university.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.app.university.domain.Course;
import com.university.app.university.domain.HalfYearGrade;
import com.university.app.university.domain.Student;
import com.university.app.university.domain.StudentCourse;
import com.university.app.university.domain.StudentCourseId;
import com.university.app.university.exception.NotExistException;
import com.university.app.university.repository.CourseRepository;
import com.university.app.university.repository.HalfYearGradeRepository;
import com.university.app.university.repository.StudentCourseRepository;
import com.university.app.university.repository.StudentRepository;
import com.university.app.university.service.HalfYearGradeService;
import com.university.app.university.service.dto.HalfYearGradeDTO;
import com.university.app.university.service.mapper.HalfYearGradeMapper;

/**
 * @author Thinh Tat
 *
 */
@Service
@Transactional
public class HalfYearGradeServiceImpl implements HalfYearGradeService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentCourseRepository studentCourseRepository;

	@Autowired
	private HalfYearGradeRepository halfYearGradeRepository;

	@Autowired
	private HalfYearGradeMapper halfYearGradeMapper;

	@Override
	@Transactional
	public HalfYearGradeDTO save(HalfYearGradeDTO halfYearGradeDTO) throws NotExistException {
		Optional<Student> student = studentRepository.findById(halfYearGradeDTO.getStudentId());
		if (!student.isPresent()) {
			throw new NotExistException("Student is not found");
		}

		Optional<Course> course = courseRepository.findById(halfYearGradeDTO.getCourseId());
		if (!course.isPresent()) {
			throw new NotExistException("Course is not found");
		}

		Optional<StudentCourse> studentCourse = studentCourseRepository
				.findById(new StudentCourseId(student.get(), course.get()));

		if (!studentCourse.isPresent()) {
			throw new NotExistException("StudentCourse is not found");
		}

		HalfYearGrade halfYearGrade = new HalfYearGrade(studentCourse.get(), halfYearGradeDTO.getHalfYearGradeId(),
				halfYearGradeDTO.getGrade());

		return halfYearGradeMapper.toDto(halfYearGradeRepository.save(halfYearGrade));
	}

}

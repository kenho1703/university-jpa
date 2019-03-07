package com.university.app.university.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.university.app.university.UniversityApplication;
import com.university.app.university.domain.Course;
import com.university.app.university.domain.HalfYearGrade;
import com.university.app.university.domain.Student;
import com.university.app.university.domain.StudentCourse;
import com.university.app.university.domain.StudentCourseId;
import com.university.app.university.exception.MaxGradeException;
import com.university.app.university.exception.NotExistException;
import com.university.app.university.repository.CourseRepository;
import com.university.app.university.repository.HalfYearGradeRepository;
import com.university.app.university.repository.StudentCourseRepository;
import com.university.app.university.repository.StudentRepository;
import com.university.app.university.service.dto.HalfYearGradeDTO;

/**
 * @author Thinh Tat
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
@Transactional
public class HalfYearGradeServiceTest {
	@MockBean
	private CourseRepository courseRepository;

	@MockBean
	private StudentRepository studentRepository;

	@MockBean
	private StudentCourseRepository studentCourseRepository;

	@MockBean
	private HalfYearGradeRepository halfYearGradeRepository;

	@Autowired
	private HalfYearGradeService halfYearGradeService;

	private Course course;

	private Student student;

	private Optional<StudentCourse> studentCourse;

	private HalfYearGrade halfYearGrade;

	private HalfYearGradeDTO halfYearGradeDTO;

	@Before
	@Transactional
	public void init() {
		course = new Course();
		course.setCourseId(12L);
		course.setName("Java Fundamentals");

		student = new Student();
		student.setStudentId(12L);
		student.setName("John Doe");

		studentCourse = Optional.of(new StudentCourse(student, course));
		halfYearGradeDTO = new HalfYearGradeDTO(student.getStudentId(), course.getCourseId(), 12L, 10);
		halfYearGrade = new HalfYearGrade(studentCourse.get(), halfYearGradeDTO.getHalfYearGradeId(),
				halfYearGradeDTO.getGrade());
	}

	@Test
	@Transactional
	public void testAddGrade() throws MaxGradeException, NotExistException {
		Mockito.when(studentCourseRepository.findById(ArgumentMatchers.<StudentCourseId>any()))
				.thenReturn(studentCourse);
		Mockito.when(halfYearGradeRepository.save(ArgumentMatchers.<HalfYearGrade>any())).thenReturn(halfYearGrade);
		Mockito.when(studentRepository.findById(student.getStudentId())).thenReturn(Optional.of(student));
		Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(Optional.of(course));
		
		HalfYearGradeDTO actual = halfYearGradeService.save(halfYearGradeDTO);

		assertThat(actual).isNotNull();
		assertThat(actual.getStudentId()).isNotNull().isEqualTo(student.getStudentId());
		assertThat(actual.getCourseId()).isNotNull().isEqualTo(course.getCourseId());
		assertThat(actual.getGrade()).isNotNull().isEqualTo(halfYearGradeDTO.getGrade());
		assertThat(actual.getHalfYearGradeId()).isNotNull().isEqualTo(halfYearGradeDTO.getHalfYearGradeId());

		Mockito.verify(studentCourseRepository).findById(ArgumentMatchers.<StudentCourseId>any());
		Mockito.verify(halfYearGradeRepository).save(ArgumentMatchers.<HalfYearGrade>any());
	}

}

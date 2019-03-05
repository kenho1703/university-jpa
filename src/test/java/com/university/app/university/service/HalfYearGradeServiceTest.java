package com.university.app.university.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.university.app.university.UniversityApplication;
import com.university.app.university.domain.Course;
import com.university.app.university.domain.Student;
import com.university.app.university.domain.StudentCourse;
import com.university.app.university.domain.University;
import com.university.app.university.exception.MaxGradeException;
import com.university.app.university.exception.NotExistException;
import com.university.app.university.repository.CourseRepository;
import com.university.app.university.repository.StudentCourseRepository;
import com.university.app.university.repository.StudentRepository;
import com.university.app.university.service.dto.HalfYearGradeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
@Transactional
public class HalfYearGradeServiceTest {
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private HalfYearGradeService halfYearGradeService;

	@Autowired
	private StudentCourseRepository studentCourseRepository;

	private University university;

	private List<Course> courses;

	private Course course;

	private Student student;

	@Before
	@Transactional
	public void init() {
		course = new Course();
		course.setName("Java Fundamentals");

		Course course1 = new Course();
		course1.setName("Programming Fundamentals");

		Course course2 = new Course();
		course2.setName("Web Programming");

		courses = new ArrayList<Course>();
		courses.add(course1);
		courses.add(course2);

		// University test data
		university = new University();
		university.setId(1L);
		university.setName("National University of VietNam");
		university.setOrgNo(1234L);

		student = new Student();
		student.setName("John Doe");
	}

	@Test
	@Transactional
	public void testAddGrade() throws MaxGradeException, NotExistException {
		Course courseForTest = courseRepository.saveAndFlush(course);
		Student studentForTest = studentRepository.saveAndFlush(student);

		StudentCourse studentCourse = new StudentCourse(studentForTest, courseForTest);
		studentCourseRepository.saveAndFlush(studentCourse);

		HalfYearGradeDTO halfYearGradeDTO = new HalfYearGradeDTO(studentForTest.getStudentId(),
				courseForTest.getCourseId(), 12L, 10);
		HalfYearGradeDTO actual = halfYearGradeService.save(halfYearGradeDTO);

		assertThat(actual).isNotNull();
		assertThat(actual.getStudentId()).isNotNull().isEqualTo(studentForTest.getStudentId());
		assertThat(actual.getCourseId()).isNotNull().isEqualTo(courseForTest.getCourseId());
		assertThat(actual.getGrade()).isNotNull().isEqualTo(halfYearGradeDTO.getGrade());
		assertThat(actual.getHalfYearGradeId()).isNotNull().isEqualTo(halfYearGradeDTO.getHalfYearGradeId());
	}

}

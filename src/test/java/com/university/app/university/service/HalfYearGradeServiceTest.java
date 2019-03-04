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
import com.university.app.university.domain.HalfYearGrade;
import com.university.app.university.domain.HalfYearGradeId;
import com.university.app.university.domain.Student;
import com.university.app.university.domain.University;
import com.university.app.university.repository.CourseRepository;
import com.university.app.university.repository.HalfYearGradeRepository;
import com.university.app.university.repository.StudentRepository;
import com.university.app.university.service.dto.StudentDTO;
import com.university.app.university.service.mapper.StudentMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
@Transactional
public class HalfYearGradeServiceTest {
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private HalfYearGradeRepository halfYearGradeRepository;

	@Autowired
	private StudentMapper studentMapper;

	private University university;

	private List<Course> courses;

	private Course course;

	@Before
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
	}

	@Test
	@Transactional
	public void testAddGrade() {
		Course johnCourse = courseRepository.save(course);
		
		Student john = new Student();
		john.setName("John Doe");
		john.addCourse(johnCourse);
		john.setUniversity(university);

		StudentDTO studentDTO = studentMapper.toDto(studentRepository.save(john));

		HalfYearGrade halfYearGrade = new HalfYearGrade(
				new HalfYearGradeId(12L, studentDTO.getStudentId(), johnCourse.getCourseId()), 10);
		HalfYearGrade actual = halfYearGradeRepository.save(halfYearGrade);

		assertThat(actual).isNotNull();
	}
}

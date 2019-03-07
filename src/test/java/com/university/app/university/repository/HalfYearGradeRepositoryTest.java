package com.university.app.university.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.university.app.university.domain.Course;
import com.university.app.university.domain.HalfYearGrade;
import com.university.app.university.domain.Student;
import com.university.app.university.domain.StudentCourse;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HalfYearGradeRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentCourseRepository studentCourseRepository;

	@Autowired
	private HalfYearGradeRepository halfYearGradeRepository;

	private Course course;

	private Student student;

	@Before
	public void setUp() {
		course = new Course();
		course.setName("Java Fundamentals");

		student = new Student();
		student.setName("John Doe");
	}

	@Test
	@Transactional
	public void testSaveCourse() {
		Course savedCourse = this.entityManager.persistAndFlush(course);
		Student savedStudent = this.entityManager.persistAndFlush(student);

		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setStudent(savedStudent);
		studentCourse.setCourse(savedCourse);

		student.getStudentCourses().add(studentCourse);
		this.entityManager.persistAndFlush(student);

		HalfYearGrade halfYearGrade = new HalfYearGrade(studentCourse, 10L, 12);
		HalfYearGrade halfYearGrade2 = new HalfYearGrade(studentCourse, 11L, 12);

		HalfYearGrade savedHalfYearGrade = halfYearGradeRepository.save(halfYearGrade);
		HalfYearGrade savedHalfYearGrade2 = halfYearGradeRepository.save(halfYearGrade2);

		assertThat(savedHalfYearGrade).isNotNull();
		assertThat(savedHalfYearGrade2).isNotNull();
		assertThat(studentRepository.count(), is(equalTo(1L)));
		assertThat(studentCourseRepository.count(), is(equalTo(1L)));
		assertThat(halfYearGradeRepository.count(), is(equalTo(2L)));
	}

}

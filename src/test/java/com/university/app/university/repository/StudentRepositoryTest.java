package com.university.app.university.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.university.app.university.domain.Student;
import com.university.app.university.domain.StudentCourse;
import com.university.app.university.domain.University;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StudentRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentCourseRepository studentCourseRepository;

	private Course course;

	private University university;

	@Before
	public void setUp() {
		course = new Course();
		course.setName("Java Fundamentals");

		university = new University();
		university.setId(1L);
		university.setName("National University of VietNam");
		university.setOrgNo(1234L);
	}

	@Test
	@Transactional
	public void testSaveStudent() {
		this.entityManager.persistAndFlush(course);
		University savedUniversity = this.entityManager.persistAndFlush(university);

		Student student = new Student();
		student.setName("John Doe");
		student.setUniversity(savedUniversity);
		Student savedStudent = this.entityManager.persistAndFlush(student);

		assertThat(savedStudent).isNotNull();
	}

	@Test
	@Transactional
	public void testDeleteStudentById() {
		Course savedCourse = this.entityManager.persistAndFlush(course);
		University savedUniversity = this.entityManager.persistAndFlush(university);

		Student student = new Student();
		student.setName("John Doe");
		student.setUniversity(savedUniversity);

		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setStudent(student);
		studentCourse.setCourse(savedCourse);

		student.getStudentCourses().add(studentCourse);
		Student savedStudent = this.entityManager.persistAndFlush(student);

		studentRepository.delete(savedStudent);
		assertThat(studentCourseRepository.count()).isZero();
		assertThat(studentRepository.count()).isZero();
	}

}

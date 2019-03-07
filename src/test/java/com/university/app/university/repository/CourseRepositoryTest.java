package com.university.app.university.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

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

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CourseRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CourseRepository courseRepository;

	private Course course;

	@Before
	public void setUp() {
		course = new Course();
		course.setName("Java Fundamentals");
	}

	@Test
	@Transactional
	public void testSaveCourse() {
		Course savedCourse = this.entityManager.persistAndFlush(course);

		assertThat(savedCourse).isNotNull();
		assertThat(savedCourse.getCourseId()).isNotNull();
		assertThat(savedCourse.getName()).isNotNull().isEqualTo(savedCourse.getName());
	}

	@Test
	@Transactional
	public void testFindAll() {
		this.entityManager.persistAndFlush(course);

		List<Course> universities = courseRepository.findAll();
		assertThat(universities).isNotNull().isNotEmpty();
		assertThat(universities).hasSize(1);
	}

	@Test
	@Transactional
	public void testFindById() {
		Long id = (Long) this.entityManager.persistAndGetId(course);

		Optional<Course> maybeCourse = courseRepository.findById(id);

		assertThat(maybeCourse).isPresent();
		assertThat(maybeCourse.orElse(null).getCourseId()).isEqualTo(id);
		assertThat(maybeCourse.orElse(null).getName()).isEqualTo(course.getName());
	}

	@Test
	@Transactional
	public void testDeleteById() {
		Long id = (Long) this.entityManager.persistAndGetId(course);

		courseRepository.deleteById(id);

		assertThat(courseRepository.count() == 0);
	}
}

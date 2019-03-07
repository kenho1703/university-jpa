package com.university.app.university.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.university.app.university.UniversityApplication;
import com.university.app.university.domain.Course;
import com.university.app.university.exception.AlreadyExistException;
import com.university.app.university.exception.NotExistException;
import com.university.app.university.repository.CourseRepository;
import com.university.app.university.service.dto.CourseDTO;

/**
 * @author Thinh Tat
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
@Transactional
public class CourseServiceTest {
	@Autowired
	private CourseService courseService;

	@MockBean
	private CourseRepository courseRepository;

	private Course course;

	private Course course2;

	private CourseDTO courseDTO;

	private Optional<Course> maybeCourse;

	private List<Course> courses;

	@Before
	public void init() {
		course = new Course();
		course.setCourseId(1L);
		course.setName("Programming Fundamentals");

		course2 = new Course();
		course2.setCourseId(1L);
		course2.setName("Programming Fundamentals");

		courseDTO = new CourseDTO();
		courseDTO.setCourseId(1L);
		courseDTO.setName("Programming Fundamentals");

		maybeCourse = Optional.of(course);

		courses = new ArrayList<>();
		courses.add(course);
		courses.add(course2);
	}

	@Test
	@Transactional
	public void testSaveCourse() throws AlreadyExistException {
		Mockito.when(courseRepository.save(course)).thenReturn(course);

		CourseDTO actual = courseService.save(courseDTO);

		assertThat(actual).isNotNull();
		assertThat(actual.getCourseId()).isNotNull().isEqualTo(courseDTO.getCourseId());
		assertThat(actual.getName()).isNotNull().isEqualTo(courseDTO.getName());
		Mockito.verify(courseRepository).save(course);
	}

	@Test(expected = AlreadyExistException.class)
	@Transactional
	public void testSaveWithExistingUniversity_ShouldThrowAlreadyExistException() throws AlreadyExistException {
		Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(maybeCourse);

		courseService.save(courseDTO);

		Mockito.verify(courseRepository).findById(course.getCourseId());
	}

	@Test
	@Transactional
	public void testFindAll() {
		Mockito.when(courseRepository.findAll()).thenReturn(courses);

		List<CourseDTO> courseList = courseService.findAll();

		assertThat(courseList).isNotNull().isNotEmpty();
		assertThat(courseList.size() == 2);
		Mockito.verify(courseRepository).findAll();
	}

	@Test
	@Transactional
	public void testFindOne() {
		Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(maybeCourse);

		Optional<CourseDTO> actual = courseService.findOne(course.getCourseId());

		assertThat(actual).isPresent();
		assertThat(actual.orElse(null).getCourseId()).isEqualTo(course.getCourseId());
		assertThat(actual.orElse(null).getName()).isEqualTo(course.getName());

		Mockito.verify(courseRepository).findById(course.getCourseId());
	}

	@Test
	@Transactional
	public void testDeleteById() throws NotExistException {
		Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(maybeCourse);

		courseService.delete(course.getCourseId());

		Mockito.verify(courseRepository).deleteById(course.getCourseId());
	}

}

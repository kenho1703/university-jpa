package com.university.app.university.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.university.app.university.UniversityApplication;
import com.university.app.university.domain.Course;
import com.university.app.university.service.dto.CourseDTO;

/**
 * @author Thinh Tat
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
public class CourseMapperTest {

	@Autowired
	private CourseMapper courseMapper;

	private Course course;

	private CourseDTO courseDTO;

	@Before
	public void init() {
		course = new Course();
		course.setCourseId(1L);
		course.setName("Java Fundamentals");

		courseDTO = new CourseDTO();
		courseDTO.setCourseId(1L);
		courseDTO.setName("Java Fundamentals");
	}

	@Test
	public void toEntityMapShouldReturnCourseEntity() {
		Course actual = courseMapper.toEntity(courseDTO);

		assertThat(actual).isNotNull();
		assertThat(actual.getCourseId()).isEqualTo(courseDTO.getCourseId());
		assertThat(actual.getName()).isEqualTo(courseDTO.getName());
	}

	@Test
	public void toDTOMapShouldReturnCourseDTO() {
		CourseDTO actual = courseMapper.toDto(course);

		assertThat(actual).isNotNull();
		assertThat(actual.getCourseId()).isEqualTo(course.getCourseId());
		assertThat(actual.getName()).isEqualTo(course.getName());
	}
}

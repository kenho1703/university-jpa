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
import com.university.app.university.domain.University;
import com.university.app.university.repository.CourseRepository;
import com.university.app.university.repository.StudentRepository;
import com.university.app.university.repository.UniversityRepository;
import com.university.app.university.service.dto.CreateStudentDTO;
import com.university.app.university.service.dto.StudentDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
@Transactional
public class StudentServiceTest {
	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentService studentService;

	private List<Course> courses;

	private University university;

	private CreateStudentDTO studentDTO;
	
	private List<Student> students;

	@Before
	public void init() {
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

		// Student test data
		studentDTO = new CreateStudentDTO();
		studentDTO.setName("Ken Ho");
		studentDTO.setUniversityId(1L);
		studentDTO.setCourseIds(new ArrayList<>());
		
		//student list test data
		Student john = new Student();
		john.setName("John Doe");
		
		Student ken = new Student();
		ken.setName("Ken Ho");
		students = new ArrayList<>();
		students.add(ken);
		students.add(john);
	}

	@Test
	@Transactional
	public void testRegisterWithUniversityAndCourse() {
		List<Course> courseList = courseRepository.saveAll(courses);
		universityRepository.saveAndFlush(university);

		for (Course course : courseList) {
			studentDTO.getCourseIds().add(course.getCourseId());
		}

		StudentDTO actual = studentService.save(studentDTO);
		assertThat(actual).isNotNull();
		assertThat(actual.getStudentId()).isNotNull();
		assertThat(actual.getName()).isNotNull().isEqualTo(studentDTO.getName());
		assertThat(actual.getUniversity()).isNotNull();
		assertThat(actual.getCourses()).isNotNull().isNotEmpty();
	}
	
	@Test
	@Transactional
	public void testFindAllStudentsShouldReturnStudentDTOList() {
		studentRepository.saveAll(students);
		
		List<StudentDTO> actual = studentService.findAll();
		
		assertThat(actual).isNotNull().isNotEmpty();
		assertThat(actual.size() == 2);
	}
	
}

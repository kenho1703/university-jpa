package com.university.app.university.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
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
import com.university.app.university.domain.Student;
import com.university.app.university.domain.StudentCourse;
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
	@MockBean
	private UniversityRepository universityRepository;

	@MockBean
	private CourseRepository courseRepository;

	@MockBean
	private StudentRepository studentRepository;

	@Autowired
	private StudentService studentService;

	private List<Course> courses;

	private University university;

	private CreateStudentDTO studentDTO;

	private Student student;

	private List<Student> students;

	private Optional<University> maybeUniversity;

	@Before
	public void init() {
		Course course1 = new Course();
		course1.setCourseId(1L);
		course1.setName("Programming Fundamentals");

		Course course2 = new Course();
		course2.setCourseId(2L);
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

		// student list test data
		Student john = new Student();
		john.setName("John Doe");

		Student ken = new Student();
		ken.setName("Ken Ho");
		students = new ArrayList<>();
		students.add(ken);
		students.add(john);

		maybeUniversity = Optional.of(university);

		Student studentRef = new Student();
		studentRef.setStudentId(123L);
		studentRef.setName("Ken Ho");

		student = new Student();
		student.setStudentId(123L);
		student.setName("Ken Ho");
		student.setUniversity(university);
		List<StudentCourse> studentCourses = new ArrayList<>();
		studentCourses.add(new StudentCourse(studentRef, course1));
		studentCourses.add(new StudentCourse(studentRef, course2));
		student.setStudentCourses(studentCourses);

	}

	@Test
	@Transactional
	public void testRegisterWithUniversityAndCourse() {
		Mockito.when(universityRepository.findById(university.getId())).thenReturn(maybeUniversity);
		Mockito.when(courseRepository.findAllById(studentDTO.getCourseIds())).thenReturn(courses);
		Mockito.when(studentRepository.save(ArgumentMatchers.<Student>any())).thenReturn(student);

		for (Course course : courses) {
			studentDTO.getCourseIds().add(course.getCourseId());
		}

		StudentDTO actual = studentService.save(studentDTO);
		assertThat(actual).isNotNull();
		assertThat(actual.getStudentId()).isNotNull().isEqualTo(student.getStudentId());
		assertThat(actual.getName()).isNotNull().isEqualTo(student.getName());
		assertThat(actual.getUniversity()).isNotNull();
		assertThat(actual.getCourses()).isNotNull().isNotEmpty();

		Mockito.verify(universityRepository).findById(university.getId());
		Mockito.verify(courseRepository).findAllById(studentDTO.getCourseIds());
		Mockito.verify(studentRepository).save(ArgumentMatchers.<Student>any());
	}

	@Test
	@Transactional
	public void testFindAllStudentsShouldReturnStudentDTOList() {
		Mockito.when(studentRepository.findAll()).thenReturn(students);

		List<StudentDTO> actual = studentService.findAll();

		assertThat(actual).isNotNull().isNotEmpty();
		assertThat(actual.size() == 2);

		Mockito.verify(studentRepository).findAll();
	}

}

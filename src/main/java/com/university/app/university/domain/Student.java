package com.university.app.university.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studen_generator")
	@SequenceGenerator(name = "studen_generator")
	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "student_name")
	private String name;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentCourse> studentCourses = new ArrayList<>();

	@ManyToOne
	private University university;

	public void addCourse(Course course) {
		StudentCourse studentCourse = new StudentCourse(this, course);
		studentCourses.add(studentCourse);
		course.getStudentCourses().add(studentCourse);
	}

	public void removeCourse(Course course) {
		for (Iterator<StudentCourse> iterator = studentCourses.iterator(); iterator.hasNext();) {
			StudentCourse studentCourse = iterator.next();

			if (studentCourse.getStudent().equals(this) && studentCourse.getCourse().equals(course)) {
				iterator.remove();
				studentCourse.getStudent().getStudentCourses().remove(studentCourse);
				studentCourse.setStudent(null);
				studentCourse.setCourse(null);
			}
		}
	}
}

package com.university.app.university.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student_course")
@Getter
@Setter
@EqualsAndHashCode
public class StudentCourse {

	@EmbeddedId
	private StudentCourseId id;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false) })
	private Student student;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false) })
	private Course course;

	public StudentCourse(Student student, Course course) {
		this.student = student;
		this.course = course;
		this.id = new StudentCourseId(student.getStudentId(), course.getCourseId());
	}
}
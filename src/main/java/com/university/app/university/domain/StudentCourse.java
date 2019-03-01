package com.university.app.university.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "student_course")
@Data
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

}
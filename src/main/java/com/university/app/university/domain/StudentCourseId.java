package com.university.app.university.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Thinh Tat
 *
 */
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class StudentCourseId implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Student student;

	@ManyToOne
	private Course course;
	
	public StudentCourseId() {
	}
	
	public StudentCourseId(Student student, Course course) {
		this.student = student;
		this.course = course;
	}
}

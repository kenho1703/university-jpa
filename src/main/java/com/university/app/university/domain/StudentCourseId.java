package com.university.app.university.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class StudentCourseId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "course_id")
	private Long courseId;

	public StudentCourseId() {
	}
	
	public StudentCourseId(Long studentId, Long courseId) {
		this.studentId = studentId;
		this.courseId = courseId;
	}
}

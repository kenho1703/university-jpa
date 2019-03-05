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
public class HalfYearGradeId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "hyg_id")
	private Long halfYearGradId;

	private StudentCourseId studentCourseId;

	public HalfYearGradeId() {
	}
	
	public HalfYearGradeId(Long studentId, Long courseId, Long halfYearGradId) {
		this.halfYearGradId = halfYearGradId;
		this.studentCourseId = new StudentCourseId(studentId, courseId);
	}
}

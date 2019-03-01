package com.university.app.university.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class HalfYearGradeId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "hyg_id")
	private Long halfYearGradId;

	private StudentCourseId studentCourseId;
}

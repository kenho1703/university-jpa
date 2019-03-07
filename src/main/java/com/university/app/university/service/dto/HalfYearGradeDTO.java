package com.university.app.university.service.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Thinh Tat
 *
 */
@Getter
@Setter
public class HalfYearGradeDTO {
	@NotNull
	private Long studentId;
	@NotNull
	private Long courseId;
	@NotNull
	private Long halfYearGradeId;
	@NotNull
	private Integer grade;

	public HalfYearGradeDTO() {
	}

	public HalfYearGradeDTO(Long studentId, Long courseId, Long halfYearGradeId, Integer grade) {
		this.studentId = studentId;
		this.courseId = courseId;
		this.halfYearGradeId = halfYearGradeId;
		this.grade = grade;
	}
}

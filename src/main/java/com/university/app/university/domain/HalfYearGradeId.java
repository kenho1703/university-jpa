package com.university.app.university.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Thinh Tat
 *
 */
@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class HalfYearGradeId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "hyg_id", unique = true, nullable = false)
	private Long halfYearGradId;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false),
			@JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false) })
	private StudentCourse studentCourse;

}

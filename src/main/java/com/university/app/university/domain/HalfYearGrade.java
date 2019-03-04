package com.university.app.university.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "halfyeargrade")
@Getter
@Setter
@EqualsAndHashCode
public class HalfYearGrade implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HalfYearGradeId id;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false),
			@JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false), })
	@Size(min = 0, max = 2)
	private StudentCourse studentCourse;

	@Column(name = "grade")
	private Integer grade;

	public HalfYearGrade(HalfYearGradeId halfYearGradeId, Integer grade) {
		this.id = halfYearGradeId;
		this.grade = grade;
	}
}

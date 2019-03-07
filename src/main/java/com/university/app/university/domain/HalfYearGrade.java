package com.university.app.university.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Thinh Tat
 *
 */
@Entity
@Table(name = "halfyeargrade")
@Getter
@Setter
@EqualsAndHashCode
public class HalfYearGrade implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HalfYearGradeId pk = new HalfYearGradeId();

	@Column(name = "grade")
	private Integer grade;

	public HalfYearGrade() {

	}

	public HalfYearGrade(StudentCourse studentCourse, Long halfYearGradId, Integer grade) {
		getPk().setStudentCourse(studentCourse);
		getPk().setHalfYearGradId(halfYearGradId);
		this.grade = grade;
	}

	public void setStudentCourse(StudentCourse studentCourse) {
		getPk().setStudentCourse(studentCourse);
	}

	public void setHalfYearGradId(Long halfYearGradId) {
		getPk().setHalfYearGradId(halfYearGradId);
	}
}

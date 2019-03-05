package com.university.app.university.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false) })
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false) })
	private Course course;

	@OneToMany(mappedBy = "studentCourse", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HalfYearGrade> halfYearGrades = new ArrayList<>();

	public StudentCourse() {
	}

	public StudentCourse(Student student, Course course) {
		this.student = student;
		this.course = course;
		this.id = new StudentCourseId(student.getStudentId(), course.getCourseId());
	}
}
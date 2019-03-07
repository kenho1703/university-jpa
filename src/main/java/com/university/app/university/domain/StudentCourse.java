package com.university.app.university.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Thinh Tat
 *
 */
@Entity
@Table(name = "student_course")
@AssociationOverrides({ @AssociationOverride(name = "pk.student", joinColumns = @JoinColumn(name = "student_id")),
		@AssociationOverride(name = "pk.course", joinColumns = @JoinColumn(name = "course_id")) })
@Getter
@Setter
@EqualsAndHashCode
public class StudentCourse implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StudentCourseId pk = new StudentCourseId();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.studentCourse", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HalfYearGrade> halfYearGrades = new LinkedList<>();

	public StudentCourse() {
	}

	public StudentCourse(Student student, Course course) {
		getPk().setStudent(student);
		getPk().setCourse(course);
	}

	public void setStudent(Student student) {
		getPk().setStudent(student);
	}

	public void setCourse(Course course) {
		getPk().setCourse(course);
	}
}
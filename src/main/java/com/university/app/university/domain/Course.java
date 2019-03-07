package com.university.app.university.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Thinh Tat
 *
 */
@Entity
@Table(name = "course")
@Getter
@Setter
@EqualsAndHashCode
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
	@SequenceGenerator(name = "location_generator")
	@Column(name = "course_id", unique = true, nullable = false)
	@NotNull
	private Long courseId;

	@Column(name = "course_name")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.course")
	private List<StudentCourse> studentCourses = new LinkedList<>();
}

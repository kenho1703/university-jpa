package com.university.app.university.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "student")
@Getter
@Setter
@EqualsAndHashCode
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studen_generator")
	@SequenceGenerator(name = "studen_generator")
	@Column(name = "student_id", unique = true, nullable = false)
	@NotNull
	private Long studentId;

	@Column(name = "student_name")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentCourse> studentCourses = new LinkedList<>();

	@ManyToOne
	private University university;

}

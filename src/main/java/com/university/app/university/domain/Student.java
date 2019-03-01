package com.university.app.university.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {

	@Id
	@GeneratedValue
	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "student_name")
	private String name;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentCourse> studentCourses = new ArrayList<>();

	@ManyToOne
	private University university;

}

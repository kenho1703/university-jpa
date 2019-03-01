package com.university.app.university.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Course {

	@Id
	@GeneratedValue
	@Column(name = "course_id")
	private Long courseId;

	@Column(name = "course_name")
	private String name;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentCourse> studentCourses = new ArrayList<>();

}

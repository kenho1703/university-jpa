package com.university.app.university.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "university")
@Getter
@Setter
@EqualsAndHashCode
public class University implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	private Long id;

	private Long orgNo;

	private String name;

	@OneToMany(mappedBy = "university")
	Set<Student> students = new HashSet<>();
}

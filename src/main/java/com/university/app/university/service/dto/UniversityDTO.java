package com.university.app.university.service.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniversityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long OrgNo;

	private String name;
}

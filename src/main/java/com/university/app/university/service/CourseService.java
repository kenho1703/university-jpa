package com.university.app.university.service;

import java.util.List;
import java.util.Optional;

import com.university.app.university.service.dto.CourseDTO;

/**
 * @author Thinh Tat
 *
 */
public interface CourseService {
	CourseDTO save(CourseDTO courseDTO);

	List<CourseDTO> findAll();

	Optional<CourseDTO> findOne(Long id);

	void delete(Long id);
}

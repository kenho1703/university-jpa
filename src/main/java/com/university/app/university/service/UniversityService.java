package com.university.app.university.service;

import java.util.List;
import java.util.Optional;

import com.university.app.university.service.dto.UniversityDTO;

public interface UniversityService {
	UniversityDTO save(UniversityDTO universityDTO);

	List<UniversityDTO> findAll();

	Optional<UniversityDTO> findOne(Long id);

	void delete(Long id);
}

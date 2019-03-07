package com.university.app.university.service;

import java.util.List;
import java.util.Optional;

import com.university.app.university.service.dto.CreateStudentDTO;
import com.university.app.university.service.dto.StudentDTO;

/**
 * @author Thinh Tat
 *
 */
public interface StudentService {
	StudentDTO save(CreateStudentDTO studentDTO);

	List<StudentDTO> findAll();

	Optional<StudentDTO> findOne(Long id);
	
	void delete(Long id);
}

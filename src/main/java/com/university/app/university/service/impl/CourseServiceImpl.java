package com.university.app.university.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.app.university.domain.Course;
import com.university.app.university.repository.CourseRepository;
import com.university.app.university.service.CourseService;
import com.university.app.university.service.dto.CourseDTO;
import com.university.app.university.service.mapper.CourseMapper;

/**
 * @author Thinh Tat
 *
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseMapper courseMapper;

	@Override
	public CourseDTO save(CourseDTO courseDTO) {
		Course course = courseMapper.toEntity(courseDTO);
		course = courseRepository.save(course);
		return courseMapper.toDto(course);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseDTO> findAll() {
		return courseRepository.findAll().stream().map(courseMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CourseDTO> findOne(Long id) {
		return courseRepository.findById(id).map(courseMapper::toDto);
	}

	@Override
	public void delete(Long id) {
		courseRepository.deleteById(id);
	}

}

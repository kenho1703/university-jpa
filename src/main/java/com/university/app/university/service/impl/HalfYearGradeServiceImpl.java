package com.university.app.university.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.app.university.domain.HalfYearGrade;
import com.university.app.university.domain.HalfYearGradeId;
import com.university.app.university.domain.StudentCourse;
import com.university.app.university.domain.StudentCourseId;
import com.university.app.university.exception.MaxGradeException;
import com.university.app.university.exception.NotExistException;
import com.university.app.university.repository.HalfYearGradeRepository;
import com.university.app.university.repository.StudentCourseRepository;
import com.university.app.university.service.HalfYearGradeService;
import com.university.app.university.service.dto.HalfYearGradeDTO;
import com.university.app.university.service.mapper.HalfYearGradeMapper;

@Service
public class HalfYearGradeServiceImpl implements HalfYearGradeService {

	@Autowired
	private StudentCourseRepository studentCourseRepository;

	@Autowired
	private HalfYearGradeRepository halfYearGradeRepository;

	@Autowired
	private HalfYearGradeMapper halfYearGradeMapper;

	@Override
	@Transactional
	public HalfYearGradeDTO save(HalfYearGradeDTO halfYearGradeDTO) throws MaxGradeException, NotExistException {

		Optional<StudentCourse> studentCourse = studentCourseRepository
				.findById(new StudentCourseId(halfYearGradeDTO.getStudentId(), halfYearGradeDTO.getCourseId()));

		if (!studentCourse.isPresent()) {
			throw new NotExistException();
		}

		HalfYearGradeId halfYearGradeId = new HalfYearGradeId(halfYearGradeDTO.getStudentId(),
				halfYearGradeDTO.getCourseId(), halfYearGradeDTO.getHalfYearGradeId());
		HalfYearGrade halfYearGrade = new HalfYearGrade(halfYearGradeId, halfYearGradeDTO.getGrade());

		return halfYearGradeMapper.toDto(halfYearGradeRepository.save(halfYearGrade));
	}

}

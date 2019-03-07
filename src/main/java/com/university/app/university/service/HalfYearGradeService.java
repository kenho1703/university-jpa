package com.university.app.university.service;

import com.university.app.university.exception.MaxGradeException;
import com.university.app.university.exception.NotExistException;
import com.university.app.university.service.dto.HalfYearGradeDTO;

/**
 * @author Thinh Tat
 *
 */
public interface HalfYearGradeService {
	HalfYearGradeDTO save(HalfYearGradeDTO halfYearGradeDTO) throws MaxGradeException, NotExistException;
}

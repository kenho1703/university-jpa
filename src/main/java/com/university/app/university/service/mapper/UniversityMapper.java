package com.university.app.university.service.mapper;

import org.mapstruct.Mapper;

import com.university.app.university.domain.University;
import com.university.app.university.service.dto.UniversityDTO;

@Mapper(componentModel = "spring", uses = {})
public interface UniversityMapper extends EntityMapper<UniversityDTO, University> {

	default University fromId(Long id) {
		if (id == null) {
			return null;
		}
		University university = new University();
		university.setId(id);
		return university;
	}
}
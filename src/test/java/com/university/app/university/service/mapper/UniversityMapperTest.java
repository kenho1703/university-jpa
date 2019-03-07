package com.university.app.university.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.university.app.university.UniversityApplication;
import com.university.app.university.domain.University;
import com.university.app.university.service.dto.UniversityDTO;

/**
 * @author Thinh Tat
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
public class UniversityMapperTest {
	@Autowired
	private UniversityMapper universityMapper;

	private University university;

	private UniversityDTO universityDTO;

	@Before
	public void init() {
		university = new University();
		university.setId(1L);
		university.setName("National University of VietNam");
		university.setOrgNo(1234L);

		universityDTO = new UniversityDTO();
		universityDTO.setId(1L);
		universityDTO.setName("National University of VietNam");
		universityDTO.setOrgNo(1234L);
	}

	@Test
	public void toEntityMapShouldReturnUniversity() {
		University actual = universityMapper.toEntity(universityDTO);

		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isEqualTo(universityDTO.getId());
		assertThat(actual.getName()).isEqualTo(universityDTO.getName());
		assertThat(actual.getOrgNo()).isEqualTo(universityDTO.getOrgNo());
	}

	@Test
	public void toDTOMapShouldReturnUniversityDTO() {
		UniversityDTO actual = universityMapper.toDto(university);

		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isEqualTo(university.getId());
		assertThat(actual.getName()).isEqualTo(university.getName());
		assertThat(actual.getOrgNo()).isEqualTo(university.getOrgNo());
	}
}

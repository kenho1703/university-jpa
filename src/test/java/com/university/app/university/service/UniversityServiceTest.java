package com.university.app.university.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.university.app.university.UniversityApplication;
import com.university.app.university.domain.University;
import com.university.app.university.exception.AlreadyExistException;
import com.university.app.university.repository.UniversityRepository;
import com.university.app.university.service.dto.UniversityDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
@Transactional
public class UniversityServiceTest {

	@Autowired
	private UniversityService universityService;

	@Autowired
	private UniversityRepository universityRepository;

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
	@Transactional
	public void testSaveUniversity() throws AlreadyExistException {
		UniversityDTO expected = universityDTO;
		UniversityDTO actual = universityService.save(universityDTO);
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull().isEqualTo(expected.getId());
		assertThat(actual.getName()).isNotNull().isEqualTo(expected.getName());
		assertThat(actual.getOrgNo()).isNotNull().isEqualTo(expected.getOrgNo());
	}

	@Test
	@Transactional
	public void testFindAll() {
		universityRepository.saveAndFlush(university);

		List<UniversityDTO> universities = universityService.findAll();
		assertThat(universities).isNotNull().isNotEmpty();
	}

	@Test
	@Transactional
	public void testFindOne() {
		universityRepository.saveAndFlush(university);

		Optional<UniversityDTO> actual = universityService.findOne(university.getId());

		assertThat(actual).isPresent();
		assertThat(actual.orElse(null).getId()).isEqualTo(university.getId());
		assertThat(actual.orElse(null).getName()).isEqualTo(university.getName());
		assertThat(actual.orElse(null).getOrgNo()).isEqualTo(university.getOrgNo());
	}

	@Test
	@Transactional
	public void testDelete() {
		universityRepository.saveAndFlush(university);

		universityService.delete(university.getId());

		Optional<University> maybeUniversity = universityRepository.findById(university.getId());

		assertThat(maybeUniversity).isNotPresent();
	}

}

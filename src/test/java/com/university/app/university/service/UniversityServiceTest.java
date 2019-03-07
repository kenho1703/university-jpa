package com.university.app.university.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.university.app.university.UniversityApplication;
import com.university.app.university.domain.University;
import com.university.app.university.exception.AlreadyExistException;
import com.university.app.university.exception.NotExistException;
import com.university.app.university.repository.UniversityRepository;
import com.university.app.university.service.dto.UniversityDTO;

/**
 * @author Thinh Tat
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
@Transactional
public class UniversityServiceTest {

	@Autowired
	private UniversityService universityService;

	@MockBean
	private UniversityRepository universityRepository;

	private University university;

	private Optional<University> maybeUniversity;

	private UniversityDTO universityDTO;

	private List<University> universities;

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

		universities = new ArrayList<>();
		universities.add(university);

		maybeUniversity = Optional.of(university);
	}

	@Test
	@Transactional
	public void testSaveUniversity() throws AlreadyExistException {
		Mockito.when(universityRepository.save(university)).thenReturn(university);

		UniversityDTO expected = universityDTO;
		UniversityDTO actual = universityService.save(universityDTO);
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull().isEqualTo(expected.getId());
		assertThat(actual.getName()).isNotNull().isEqualTo(expected.getName());
		assertThat(actual.getOrgNo()).isNotNull().isEqualTo(expected.getOrgNo());
		Mockito.verify(universityRepository).save(university);
	}

	@Test
	@Transactional
	public void testFindAll() {
		Mockito.when(universityRepository.findAll()).thenReturn(universities);

		List<UniversityDTO> universities = universityService.findAll();
		assertThat(universities).isNotNull().isNotEmpty();

		Mockito.verify(universityRepository).findAll();
	}

	@Test
	@Transactional
	public void testFindOne() {
		Mockito.when(universityRepository.findById(university.getId())).thenReturn(maybeUniversity);

		Optional<UniversityDTO> actual = universityService.findOne(university.getId());

		assertThat(actual).isPresent();
		assertThat(actual.orElse(null).getId()).isEqualTo(university.getId());
		assertThat(actual.orElse(null).getName()).isEqualTo(university.getName());
		assertThat(actual.orElse(null).getOrgNo()).isEqualTo(university.getOrgNo());

		Mockito.verify(universityRepository).findById(university.getId());
	}

	@Test
	@Transactional
	public void testDeleteById() throws NotExistException {
		Mockito.when(universityRepository.findById(university.getId())).thenReturn(maybeUniversity);

		universityService.delete(university.getId());

		Mockito.verify(universityRepository).deleteById(university.getId());
	}

	@Test(expected = NotExistException.class)
	@Transactional
	public void testDeleteWithWrongId_ShouldReturnNotExistException() throws NotExistException {
		Mockito.when(universityRepository.findById(university.getId())).thenReturn(maybeUniversity);

		universityService.delete(2L);

		Mockito.verify(universityRepository).deleteById(2L);
	}
}

package com.university.app.university.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.university.app.university.domain.University;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UniversityRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UniversityRepository universityRepository;

	private University university;

	@Before
	public void setUp() {
		university = new University();
		university.setId(10L);
		university.setName("National of University");
		university.setOrgNo(10L);

	}

	@Test
	@Transactional
	public void testSaveUniversity() {
		University savedUniversity = this.entityManager.persistAndFlush(university);

		assertThat(savedUniversity).isNotNull();
		assertThat(savedUniversity.getId()).isNotNull().isEqualTo(university.getId());
		assertThat(savedUniversity.getName()).isNotNull().isEqualTo(university.getName());
		assertThat(savedUniversity.getOrgNo()).isNotNull().isEqualTo(university.getOrgNo());
	}

	@Test
	@Transactional
	public void testFindAll() {
		this.entityManager.persistAndFlush(university);

		List<University> universities = universityRepository.findAll();
		assertThat(universities).isNotNull().isNotEmpty();
		assertThat(universities).hasSize(1);
	}

	@Test
	@Transactional
	public void testFindById() {
		this.entityManager.persistAndFlush(university);

		Optional<University> maybeUniversity = universityRepository.findById(university.getId());

		assertThat(maybeUniversity).isPresent();
		assertThat(maybeUniversity.orElse(null).getId()).isEqualTo(university.getId());
		assertThat(maybeUniversity.orElse(null).getName()).isEqualTo(university.getName());
		assertThat(maybeUniversity.orElse(null).getOrgNo()).isEqualTo(university.getOrgNo());
	}

	@Test
	@Transactional
	public void testDeleteById() {
		this.entityManager.persistAndFlush(university);

		universityRepository.deleteById(university.getId());

		assertThat(universityRepository.count() == 0);
	}
}
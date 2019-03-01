package com.university.app.university.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.university.app.university.UniversityApplication;
import com.university.app.university.service.dto.UniversityDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
@Transactional
public class UniversityServiceTest {
	
	@Autowired
	private UniversityService universityService;
	
	@Before
    public void init() {
		
	}
	
	@Test
    @Transactional
    public void testFindAll() {
		List<UniversityDTO> universities = universityService.findAll();
		assertThat(universities).isNotNull();
	}
}

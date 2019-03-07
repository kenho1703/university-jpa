package com.university.app.university.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import com.university.app.university.UniversityApplication;
import com.university.app.university.domain.University;
import com.university.app.university.exception.ExceptionTranslator;
import com.university.app.university.repository.UniversityRepository;
import com.university.app.university.service.UniversityService;
import com.university.app.university.service.dto.UniversityDTO;

/**
 * @author Thinh Tat
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApplication.class)
public class UniversityResourceTest {

	@Autowired
	private UniversityService universityService;

	@Autowired
	private ExceptionTranslator exceptionTranslator;

	@Autowired
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	@Autowired
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Autowired
	private Validator validator;

	@Autowired
	private UniversityRepository universityRepository;

	private MockMvc restMockMvc;

	private UniversityDTO universityDTO;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		UniversityResource universityResource = new UniversityResource(universityService);
		this.restMockMvc = MockMvcBuilders.standaloneSetup(universityResource)
				.setCustomArgumentResolvers(pageableArgumentResolver).setControllerAdvice(exceptionTranslator)
				.setConversionService(createFormattingConversionService()).setMessageConverters(jacksonMessageConverter)
				.setValidator(validator).build();
	}

	@Before
	public void initTest() {
		universityDTO = new UniversityDTO();
		universityDTO.setId(10L);
		universityDTO.setName("National of University");
		universityDTO.setOrgNo(10L);
	}

	@Test
	@Transactional
	public void createUniversity() throws Exception {
		int databaseSizeBeforeCreate = universityRepository.findAll().size();

		// Create the University
		restMockMvc.perform(post("/api/university").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(universityDTO))).andExpect(status().isCreated());

		// Validate the University in the database
		List<University> universities = universityRepository.findAll();

		assertThat(universities).hasSize(databaseSizeBeforeCreate + 1);
		University createdUniversity = universities.get(universities.size() - 1);
		assertThat(createdUniversity.getId()).isEqualTo(universityDTO.getId());
		assertThat(createdUniversity.getName()).isEqualTo(universityDTO.getName());
		assertThat(createdUniversity.getOrgNo()).isEqualTo(universityDTO.getOrgNo());
	}

	@Test
	@Transactional
	public void createUniversityWithExistingId() throws Exception {
		// Create the University
		restMockMvc.perform(post("/api/university").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(universityDTO)));

		restMockMvc.perform(post("/api/university").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(universityDTO))).andExpect(status().isBadRequest());

	}

	/**
	 * Create a FormattingConversionService which use ISO date format, instead of
	 * the localized one.
	 * 
	 * @return the FormattingConversionService
	 */
	public static FormattingConversionService createFormattingConversionService() {
		DefaultFormattingConversionService dfcs = new DefaultFormattingConversionService();
		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setUseIsoFormat(true);
		registrar.registerFormatters(dfcs);
		return dfcs;
	}

}

package com.university.app.university.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.app.university.exception.AlreadyExistException;
import com.university.app.university.exception.BadRequestAlertException;
import com.university.app.university.rest.util.HeaderUtil;
import com.university.app.university.service.UniversityService;
import com.university.app.university.service.dto.UniversityDTO;

@RestController
@RequestMapping("/api")
public class UniversityResource {
	private final Logger log = LoggerFactory.getLogger(UniversityResource.class);

	private static final String ENTITY_NAME = "university";

	private UniversityService universityService;

	public UniversityResource(UniversityService universityService) {
		this.universityService = universityService;
	}

	@PostMapping("/university")
	public ResponseEntity<UniversityDTO> createUniversity(@RequestBody UniversityDTO university)
			throws URISyntaxException {
		log.debug("REST request to save university : {}", university);
		UniversityDTO result;
		try {
			result = universityService.save(university);
		} catch (AlreadyExistException e) {
			throw new BadRequestAlertException("ID is already existed", ENTITY_NAME, "idexists");
		}
		return ResponseEntity.created(new URI("/api/university/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}
}

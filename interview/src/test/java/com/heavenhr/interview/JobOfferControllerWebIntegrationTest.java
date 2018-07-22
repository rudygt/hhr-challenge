package com.heavenhr.interview;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.heavenhr.interview.dto.JobOfferDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class JobOfferControllerWebIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testList() {

		ResponseEntity<JobOfferDTO[]> response = restTemplate.getForEntity(createURLWithPort("/api/v1/joboffers"),
				JobOfferDTO[].class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody().length, is(3));
	}

	@Test
	public void testGet() {

		ResponseEntity<JobOfferDTO> response = restTemplate.getForEntity(createURLWithPort("/api/v1/joboffers/1"),
				JobOfferDTO.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));

		JobOfferDTO first = response.getBody();

		assertThat(first.getJobTitle(), is("Senior BE Developer"));
		assertThat(first.getNumberOfApplications(), is(1));
	}
	
	@Test
	public void testGetNonExisting() {

		ResponseEntity<JobOfferDTO> response = restTemplate.getForEntity(createURLWithPort("/api/v1/joboffers/50"),
				JobOfferDTO.class);

		assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));	
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}

package com.heavenhr.interview;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.heavenhr.interview.dto.CreateJobOffer;
import com.heavenhr.interview.dto.JobApplicationDTO;
import com.heavenhr.interview.dto.JobOfferDTO;
import com.heavenhr.interview.dto.UpdateJobOffer;

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

	@Test
	public void testCreate() {
		CreateJobOffer offer = new CreateJobOffer();

		offer.setJobTitle("TEST TITLE");
		offer.setStartDate(new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime());

		HttpEntity<CreateJobOffer> request = new HttpEntity<>(offer);

		ResponseEntity<JobOfferDTO> response = restTemplate.postForEntity(createURLWithPort("/api/v1/joboffers"),
				request, JobOfferDTO.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));

		JobOfferDTO first = response.getBody();

		assertThat(first.getJobTitle(), is("TEST TITLE"));
	}

	@Test
	public void testCreateDuplicate() {
		CreateJobOffer offer = new CreateJobOffer();

		offer.setJobTitle("TEST TITLE Y");
		offer.setStartDate(new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime());

		HttpEntity<CreateJobOffer> request = new HttpEntity<>(offer);

		ResponseEntity<JobOfferDTO> response = restTemplate.postForEntity(createURLWithPort("/api/v1/joboffers"),
				request, JobOfferDTO.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));

		JobOfferDTO first = response.getBody();

		assertThat(first.getJobTitle(), is("TEST TITLE Y"));

		response = restTemplate.postForEntity(createURLWithPort("/api/v1/joboffers"), request, JobOfferDTO.class);

		assertThat(response.getStatusCode(), is(HttpStatus.CONFLICT));
	}

	@Test
	public void testUpdate() {
		UpdateJobOffer offer = new UpdateJobOffer();

		offer.setJobTitle("TEST UPDATE");

		HttpEntity<UpdateJobOffer> request = new HttpEntity<>(offer);

		ResponseEntity<JobOfferDTO> response = restTemplate.exchange(createURLWithPort("/api/v1/joboffers/3"),
				HttpMethod.PUT, request, JobOfferDTO.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));

		JobOfferDTO first = response.getBody();

		assertThat(first.getId(), is(3L));
		assertThat(first.getJobTitle(), is("TEST UPDATE"));
	}

	@Test
	public void testUpdateDuplicate() {
		UpdateJobOffer offer = new UpdateJobOffer();

		offer.setJobTitle("TEST UPDATE Z");

		HttpEntity<UpdateJobOffer> request = new HttpEntity<>(offer);

		ResponseEntity<JobOfferDTO> response = restTemplate.exchange(createURLWithPort("/api/v1/joboffers/3"),
				HttpMethod.PUT, request, JobOfferDTO.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));

		JobOfferDTO first = response.getBody();

		assertThat(first.getId(), is(3L));
		assertThat(first.getJobTitle(), is("TEST UPDATE Z"));

		response = restTemplate.exchange(createURLWithPort("/api/v1/joboffers/2"), HttpMethod.PUT, request,
				JobOfferDTO.class);

		assertThat(response.getStatusCode(), is(HttpStatus.CONFLICT));
	}

	@Test
	public void testDelete() {
		CreateJobOffer offer = new CreateJobOffer();

		offer.setJobTitle("TEST TITLE W");
		offer.setStartDate(new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime());

		HttpEntity<CreateJobOffer> request = new HttpEntity<>(offer);

		ResponseEntity<JobOfferDTO> response = restTemplate.postForEntity(createURLWithPort("/api/v1/joboffers"),
				request, JobOfferDTO.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));

		JobOfferDTO first = response.getBody();

		assertThat(first.getJobTitle(), is("TEST TITLE W"));

		String url = createURLWithPort("/api/v1/joboffers/" + first.getId().toString());

		restTemplate.delete(url);

		response = restTemplate.getForEntity(url, JobOfferDTO.class);

		assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}

	@Test
	public void testListApplications() {

		ResponseEntity<JobApplicationDTO[]> response = restTemplate
				.getForEntity(createURLWithPort("/api/v1/joboffers/1/applications"), JobApplicationDTO[].class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));

		assertThat(response.getBody().length, is(1));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}

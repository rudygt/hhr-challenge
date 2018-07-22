package com.heavenhr.interview;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.heavenhr.interview.controller.JobOfferController;
import com.heavenhr.interview.dto.CreateJobOffer;
import com.heavenhr.interview.dto.JobApplicationDTO;
import com.heavenhr.interview.dto.JobOfferDTO;
import com.heavenhr.interview.dto.UpdateJobOffer;
import com.heavenhr.interview.model.JobApplication;
import com.heavenhr.interview.model.JobOffer;
import com.heavenhr.interview.service.JobOfferService;

import static java.util.Arrays.asList;

public class JobOfferControllerTest {

	private static ArrayList<JobOffer> offers;

	static {
		offers = new ArrayList<JobOffer>();

		JobOffer o = new JobOffer();

		o.setId(1L);
		o.setJobTitle("JOB A");
		o.setNumberOfApplications(1);
		o.setStartDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());

		o.setApplications( asList(new JobApplication()) );
		
		offers.add(o);

		o = new JobOffer();

		o.setId(2L);
		o.setJobTitle("JOB B");
		o.setNumberOfApplications(0);
		o.setStartDate(new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime());

		offers.add(o);
	}

	@InjectMocks
	private JobOfferController sut;

	@Mock
	private JobOfferService jobOfferService;

	@Spy
	private ModelMapper modelMapper = new ModelMapper();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testJobOfferList() {

		when(jobOfferService.getAll()).thenReturn(offers);

		List<JobOfferDTO> items = sut.list();

		assertEquals(2, items.size());

		verify(jobOfferService).getAll();

		JobOfferDTO first = items.get(0);

		assertThat(first.getId(), is(1L));
		assertThat(first.getJobTitle(), is("JOB A"));		
	}

	@Test
	public void testJobOfferGet() {
		when(jobOfferService.getJobOfferById(1L)).thenReturn(offers.get(0));

		JobOfferDTO item = sut.get(1L);

		verify(jobOfferService).getJobOfferById(1L);

		assertThat(item.getId(), is(1L));
		assertThat(item.getJobTitle(), is("JOB A"));
	}

	@Test
	public void testJobOfferCreate() {
		CreateJobOffer offer = new CreateJobOffer();

		when(jobOfferService.createJobOffer(offer)).thenReturn(offers.get(0));

		JobOfferDTO item = sut.create(offer);

		verify(jobOfferService).createJobOffer(offer);

		assertThat(item.getId(), is(1L));
		assertThat(item.getJobTitle(), is("JOB A"));
	}

	@Test
	public void tesJobOfferUpdate() {
		UpdateJobOffer offer = new UpdateJobOffer();

		when(jobOfferService.updateJobOffer(1L, offer)).thenReturn(offers.get(0));

		JobOfferDTO item = sut.update(1L, offer);

		verify(jobOfferService).updateJobOffer(1L, offer);

		assertThat(item.getId(), is(1L));
		assertThat(item.getJobTitle(), is("JOB A"));
	}

	@Test
	public void tesJobOfferDelete() {

		ResponseEntity<Void> item = sut.delete(1L);

		verify(jobOfferService).deleteJobOffer(1L);

		assertThat(item.getStatusCode(), is(HttpStatus.OK));		
	}
	
	@Test
	public void testJobOfferGetApplications() {
		when(jobOfferService.getJobOfferById(1L)).thenReturn(offers.get(0));

		List<JobApplicationDTO>  item = sut.listApplications(1L);

		verify(jobOfferService).getJobOfferById(1L);
		
		assertThat(item.size(), is(1));		
	}
}

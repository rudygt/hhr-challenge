package com.heavenhr.interview;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.heavenhr.interview.controller.JobOfferController;
import com.heavenhr.interview.dto.CreateJobOffer;
import com.heavenhr.interview.dto.JobApplicationDTO;
import com.heavenhr.interview.dto.JobOfferDTO;
import com.heavenhr.interview.dto.UpdateJobOffer;
import com.heavenhr.interview.exception.DuplicateJobOfferException;
import com.heavenhr.interview.exception.EntityNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Transactional
public class JobOfferControllerIntegrationTest {

	@Autowired
	private JobOfferController jobOfferController;
	
	@Test
	public void testList()
	{
		List<JobOfferDTO> offers = jobOfferController.list();
		
		assertThat(offers.size(), is(3));
		
		JobOfferDTO first = offers.get(0);
		
		assertThat(first.getId(), is(1L));
		assertThat(first.getJobTitle(), is("Senior BE Developer"));
		assertThat(first.getNumberOfApplications(), is(1));
	}
	
	@Test
	public void testGet()
	{
		JobOfferDTO first = jobOfferController.get(1L);
		
		assertThat(first.getId(), is(1L));
		assertThat(first.getJobTitle(), is("Senior BE Developer"));
		assertThat(first.getNumberOfApplications(), is(1));
	}
	
	@Test
	public void testCreate()
	{
		CreateJobOffer offer = new CreateJobOffer();
		
		offer.setJobTitle("TEST TITLE");
		offer.setStartDate(new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime());
		
		JobOfferDTO first = jobOfferController.create(offer);
				
		assertThat(first.getId(), greaterThan(3L));
		assertThat(first.getJobTitle(), is("TEST TITLE"));		
	}
	
	@Test(expected = DuplicateJobOfferException.class)
	public void testCreateDuplicate()
	{
		CreateJobOffer offer = new CreateJobOffer();
		
		offer.setJobTitle("TEST TITLE Y");
		offer.setStartDate(new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime());
		
		JobOfferDTO first = jobOfferController.create(offer);					
						
		assertThat(first.getId(), greaterThan(3L));
		assertThat(first.getJobTitle(), is("TEST TITLE Y"));
		
		JobOfferDTO second = jobOfferController.create(offer);
	}
	
	@Test
	public void testUpdate()
	{
		UpdateJobOffer offer = new UpdateJobOffer();
		
		offer.setJobTitle("TEST UPDATE");
		
		JobOfferDTO before = jobOfferController.get(1L);
		
		JobOfferDTO after = jobOfferController.update(1L, offer);
		
		assertThat(before.getId(), is(1L));
		assertThat(before.getJobTitle(), is("Senior BE Developer"));
		
		assertThat(after.getId(), is(1L));
		assertThat(after.getJobTitle(), is("TEST UPDATE"));
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDelete()
	{
		CreateJobOffer offer = new CreateJobOffer();
		
		offer.setJobTitle("TEST TITLE X");
		offer.setStartDate(new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime());
		
		JobOfferDTO first = jobOfferController.create(offer);
				
		assertThat(first.getId(), greaterThan(3L));
		assertThat(first.getJobTitle(), is("TEST TITLE X"));		
		
		jobOfferController.delete(first.getId());
		
		first = jobOfferController.get(first.getId());
	}
	
	@Test
	public void testListApplications()
	{
		List<JobApplicationDTO> applications = jobOfferController.listApplications(1L);
								
		assertThat(applications.size(), is(1));
		
		JobApplicationDTO first = applications.get(0);
		
		assertThat(first.getId(), is(1L));
		assertThat(first.getCandidateEmail(), is("rudygt@gmail.com"));
	}
	
}

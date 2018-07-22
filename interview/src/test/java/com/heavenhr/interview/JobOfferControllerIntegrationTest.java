package com.heavenhr.interview;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.heavenhr.interview.model.JobOffer;
import com.heavenhr.interview.service.JobOfferService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class JobOfferControllerIntegrationTest {

	@Autowired
	private JobOfferService jobOfferService;
	
	@Test
	public void testFindAll()
	{
		List<JobOffer> offers = jobOfferService.getAll();
		
		assertThat(offers.size(), is(3));
		
		JobOffer first = offers.get(0);
		
		assertThat(first.getId(), is(1L));
		assertThat(first.getJobTitle(), is("Senior BE Developer"));
	}
}

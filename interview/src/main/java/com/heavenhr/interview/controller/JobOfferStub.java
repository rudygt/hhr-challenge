package com.heavenhr.interview.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.heavenhr.interview.model.JobOffer;

public class JobOfferStub {

	private static Map<Long,JobOffer> mocks = new HashMap<Long,JobOffer>();
		
	static {
		JobOffer o = new JobOffer();
		
		o.setId(1L);
		o.setJobTitle("Senior BE Developer");
		o.setStartDate(new Date());
		o.setNumberOfApplications(1);
		
		mocks.put(o.getId(), o);
		
		o = new JobOffer();
		
		o.setId(2L);
		o.setJobTitle("Junior BE Developer");
		o.setStartDate(new Date());
		o.setNumberOfApplications(0);
		
		mocks.put(o.getId(), o);
		
		o = new JobOffer();
		
		o.setId(3L);
		o.setJobTitle("Senior QA Developer");
		o.setStartDate(new Date());
		o.setNumberOfApplications(0);
		
		mocks.put(o.getId(), o);
	}

	public static List<JobOffer> list()
	{
		return new ArrayList<JobOffer>(mocks.values());
	}

	public static JobOffer get(Long id) {
		return mocks.get(id);
	}

	public static JobOffer create(JobOffer jobOffer) {
		return mocks.put(jobOffer.getId(), jobOffer);		
	}
}

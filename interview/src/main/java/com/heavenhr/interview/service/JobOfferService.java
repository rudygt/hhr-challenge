package com.heavenhr.interview.service;

import java.util.List;

import com.heavenhr.interview.dto.ApplyToJobOffer;
import com.heavenhr.interview.dto.CreateJobOffer;
import com.heavenhr.interview.dto.UpdateJobOffer;
import com.heavenhr.interview.model.JobApplication;
import com.heavenhr.interview.model.JobOffer;

public interface JobOfferService {	

	List<JobOffer> getAll();
	
	JobOffer getJobOfferById(Long id);
	
	JobOffer createJobOffer(CreateJobOffer command);
	
	JobOffer updateJobOffer(Long id, UpdateJobOffer command);
	
	void deleteJobOffer(Long id);
		
	JobApplication applyToJobOffer(Long jobOfferId, ApplyToJobOffer command);
}

package com.heavenhr.interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.interview.dto.CreateJobOffer;
import com.heavenhr.interview.dto.UpdateJobOffer;
import com.heavenhr.interview.exception.EntityNotFoundException;
import com.heavenhr.interview.model.JobOffer;
import com.heavenhr.interview.repository.JobOfferRepository;

@Service
public class JobOfferServiceImpl implements JobOfferService {

	@Autowired
	private JobOfferRepository jobOfferRepository;

	@Override
	public List<JobOffer> getAll() {
		return jobOfferRepository.findAll();
	}

	@Override
	public JobOffer getJobOfferById(Long id) {
		return jobOfferRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	public JobOffer createJobOffer(CreateJobOffer command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobOffer updateJobOffer(Long id, UpdateJobOffer command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteJobOffer(Long id) {
		// TODO Auto-generated method stub

	}

}

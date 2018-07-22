package com.heavenhr.interview.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.heavenhr.interview.dto.CreateJobOffer;
import com.heavenhr.interview.dto.UpdateJobOffer;
import com.heavenhr.interview.exception.DuplicateJobOfferException;
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
		JobOffer offer = new JobOffer();

		BeanUtils.copyProperties(command, offer);

		try {
			offer = jobOfferRepository.saveAndFlush(offer);
		} catch (DataIntegrityViolationException uniqueViolation) {
			throw new DuplicateJobOfferException();
		}

		return offer;
	}

	@Override
	public JobOffer updateJobOffer(Long id, UpdateJobOffer command) {
		JobOffer current = jobOfferRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

		BeanUtils.copyProperties(command, current);

		try {
			return jobOfferRepository.saveAndFlush(current);
		} catch (DataIntegrityViolationException uniqueViolation) {
			throw new DuplicateJobOfferException();
		}
	}

	@Override
	public void deleteJobOffer(Long id) {
		JobOffer current = jobOfferRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

		jobOfferRepository.delete(current);
	}

}

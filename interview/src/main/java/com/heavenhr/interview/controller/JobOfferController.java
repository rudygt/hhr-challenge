package com.heavenhr.interview.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.interview.model.JobOffer;
import com.heavenhr.interview.repository.JobOfferRepository;
import com.heavenhr.interview.service.JobOfferService;

@RestController
@RequestMapping("api/v1/")
public class JobOfferController {

	@Autowired
	private JobOfferRepository jobOfferRepository;
	
	@Autowired
	private JobOfferService jobOfferService;

	@RequestMapping(value = "joboffers", method = RequestMethod.GET)
	public List<JobOffer> list() {
		return jobOfferService.getAll();
	}

	@RequestMapping(value = "joboffers", method = RequestMethod.POST)
	public JobOffer create(@RequestBody JobOffer jobOffer) {
		return jobOfferRepository.saveAndFlush(jobOffer);
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.GET)
	public JobOffer get(@PathVariable Long id) {
		return jobOfferService.getJobOfferById(id);
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.PUT)
	public JobOffer update(@PathVariable Long id, @RequestBody JobOffer jobOffer) {
		JobOffer current = jobOfferRepository.findById(id).orElse(null);

		if (current != null) {
			BeanUtils.copyProperties(jobOffer, current);
			jobOfferRepository.saveAndFlush(current);
		}

		return null;
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.DELETE)
	public JobOffer delete(@PathVariable Long id) {
		JobOffer current = jobOfferRepository.findById(id).orElse(null);

		if (current != null) {
			jobOfferRepository.delete(current);
		}

		return current;
	}
}

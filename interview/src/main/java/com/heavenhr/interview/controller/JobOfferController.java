package com.heavenhr.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.interview.dto.CreateJobOffer;
import com.heavenhr.interview.dto.UpdateJobOffer;
import com.heavenhr.interview.model.JobOffer;
import com.heavenhr.interview.service.JobOfferService;

@RestController
@RequestMapping("api/v1/")
public class JobOfferController {

	@Autowired
	private JobOfferService jobOfferService;

	@RequestMapping(value = "joboffers", method = RequestMethod.GET)
	public List<JobOffer> list() {
		return jobOfferService.getAll();
	}

	@RequestMapping(value = "joboffers", method = RequestMethod.POST)
	public JobOffer create(@RequestBody CreateJobOffer jobOffer) {
		return jobOfferService.createJobOffer(jobOffer);
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.GET)
	public JobOffer get(@PathVariable Long id) {
		return jobOfferService.getJobOfferById(id);
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.PUT)
	public JobOffer update(@PathVariable Long id, @RequestBody UpdateJobOffer jobOffer) {
		return jobOfferService.updateJobOffer(id, jobOffer);
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		jobOfferService.deleteJobOffer(id);
		return ResponseEntity.ok().build();
	}
}

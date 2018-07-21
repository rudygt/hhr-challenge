package com.heavenhr.interview.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.interview.model.JobOffer;

@RestController
@RequestMapping("api/v1/")
public class JobOfferController {

	@RequestMapping(value = "joboffers", method = RequestMethod.GET)
	public List<JobOffer> list() {
		return JobOfferStub.list();
	}

	@RequestMapping(value = "joboffers", method = RequestMethod.POST)
	public JobOffer create(@RequestBody JobOffer jobOffer) {
		return JobOfferStub.create(jobOffer);
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.GET)
	public JobOffer get(@PathVariable Long id) {
		return JobOfferStub.get(id);
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.PUT)
	public JobOffer update(@PathVariable Long id, @RequestBody JobOffer jobOffer) {
		return null;
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.DELETE)
	public JobOffer delete(@PathVariable Long id) {
		return null;
	}
}

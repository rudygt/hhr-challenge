package com.heavenhr.interview.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.interview.dto.CreateJobOffer;
import com.heavenhr.interview.dto.JobApplicationDTO;
import com.heavenhr.interview.dto.JobOfferDTO;
import com.heavenhr.interview.dto.UpdateJobOffer;
import com.heavenhr.interview.model.JobApplication;
import com.heavenhr.interview.model.JobOffer;
import com.heavenhr.interview.service.JobOfferService;

@RestController
@RequestMapping("api/v1/")
public class JobOfferController {

	@Autowired
	private JobOfferService jobOfferService;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(value = "joboffers", method = RequestMethod.GET)
	public List<JobOfferDTO> list() {
		return jobOfferService.getAll().stream().map(item -> convertToDto(item)).collect(Collectors.toList());
	}

	@RequestMapping(value = "joboffers", method = RequestMethod.POST)
	public JobOfferDTO create(@RequestBody CreateJobOffer jobOffer) {
		return convertToDto(jobOfferService.createJobOffer(jobOffer));
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.GET)
	public JobOfferDTO get(@PathVariable Long id) {
		return convertToDto(jobOfferService.getJobOfferById(id));
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.PUT)
	public JobOfferDTO update(@PathVariable Long id, @RequestBody UpdateJobOffer jobOffer) {
		return convertToDto(jobOfferService.updateJobOffer(id, jobOffer));
	}

	@RequestMapping(value = "joboffers/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		jobOfferService.deleteJobOffer(id);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "joboffers/{id}/applications", method = RequestMethod.GET)
	public List<JobApplicationDTO> listApplications(@PathVariable Long id) {
		JobOffer offer = jobOfferService.getJobOfferById(id);

		return offer.getApplications().stream().map(item -> convertToDto(item)).collect(Collectors.toList());
	}

	private JobApplicationDTO convertToDto(JobApplication application) {
		JobApplicationDTO dto = modelMapper.map(application, JobApplicationDTO.class);
		return dto;
	}

	private JobOfferDTO convertToDto(JobOffer offer) {
		JobOfferDTO dto = modelMapper.map(offer, JobOfferDTO.class);
		return dto;
	}
}

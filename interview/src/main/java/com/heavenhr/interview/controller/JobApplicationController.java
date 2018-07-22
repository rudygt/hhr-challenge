package com.heavenhr.interview.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.interview.dto.ApplyToJobOffer;
import com.heavenhr.interview.dto.JobApplicationDTO;
import com.heavenhr.interview.dto.JobOfferDTO;
import com.heavenhr.interview.dto.UpdateJobApplication;
import com.heavenhr.interview.model.JobApplication;
import com.heavenhr.interview.service.JobApplicationService;
import com.heavenhr.interview.service.JobOfferService;

@RestController
@RequestMapping("api/v1/")
public class JobApplicationController {

	@Autowired
	private JobOfferService jobOfferService;
	
	@Autowired
	private JobApplicationService jobApplicationService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@RequestMapping(value = "joboffers/{id}/apply", method = RequestMethod.POST)
	public JobApplicationDTO apply(@PathVariable Long id, @RequestBody ApplyToJobOffer applyToJobOffer) {
		return convertToDto(jobOfferService.applyToJobOffer(id, applyToJobOffer));
	}
	
	@RequestMapping(value = "jobapplications", method = RequestMethod.GET)
	public List<JobApplicationDTO> list() {
		return jobApplicationService.getAll().stream().map(item -> convertToDto(item)).collect(Collectors.toList());
	}
	
	@RequestMapping(value = "jobapplications/{id}", method = RequestMethod.GET)
	public JobApplicationDTO get(@PathVariable Long id) {
		return convertToDto(jobApplicationService.getJobApplicationById(id));
	}
	
	@RequestMapping(value = "jobapplications/{id}", method = RequestMethod.POST)
	public JobApplicationDTO update(@PathVariable Long id, @RequestBody UpdateJobApplication application) {
		return convertToDto(jobApplicationService.updateJobApplication(id, application));
	}
	
	private JobApplicationDTO convertToDto(JobApplication application) {
		JobApplicationDTO dto = modelMapper.map(application, JobApplicationDTO.class);
		return dto;
	}
}

package com.heavenhr.interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.interview.dto.UpdateJobApplication;
import com.heavenhr.interview.exception.EntityNotFoundException;
import com.heavenhr.interview.model.JobApplication;
import com.heavenhr.interview.repository.JobApplicationRepository;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	@Override
	public JobApplication updateJobApplication(Long id, UpdateJobApplication command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobApplication> getAll() {
		return jobApplicationRepository.findAll();
	}

	@Override
	public JobApplication getJobApplicationById(Long id) {
		return jobApplicationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	public void deleteJobApplication(Long id) {
		JobApplication current = jobApplicationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

		jobApplicationRepository.delete(current);
	}

}

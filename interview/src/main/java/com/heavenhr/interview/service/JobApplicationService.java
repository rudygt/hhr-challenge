package com.heavenhr.interview.service;

import java.util.List;

import com.heavenhr.interview.dto.UpdateJobApplication;
import com.heavenhr.interview.model.ApplicationStatus;
import com.heavenhr.interview.model.JobApplication;

public interface JobApplicationService {
	
	List<JobApplication> getAll();

	JobApplication getJobApplicationById(Long id);
	
	JobApplication updateJobApplication(Long id, UpdateJobApplication command);
	
	JobApplication updateJobApplicationStatus(Long id, ApplicationStatus status);
	
	void deleteJobApplication(Long id);
}

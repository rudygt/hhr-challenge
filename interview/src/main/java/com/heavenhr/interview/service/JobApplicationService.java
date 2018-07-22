package com.heavenhr.interview.service;

import java.util.List;

import com.heavenhr.interview.dto.UpdateJobApplication;
import com.heavenhr.interview.model.JobApplication;

public interface JobApplicationService {
	JobApplication updateJobApplication(Long id, UpdateJobApplication command);

	List<JobApplication> getAll();

	JobApplication getJobApplicationById(Long id);

	void deleteJobApplication(Long id);
}

package com.heavenhr.interview.service;

import com.heavenhr.interview.model.ApplicationStatus;
import com.heavenhr.interview.model.JobApplication;

public interface ApplicationStatusService {
	void handleStatusChange(JobApplication entity, ApplicationStatus oldStatus, ApplicationStatus newStatus);
}

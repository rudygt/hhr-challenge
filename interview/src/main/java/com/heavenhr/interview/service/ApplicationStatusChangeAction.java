package com.heavenhr.interview.service;

import com.heavenhr.interview.model.ApplicationStatus;
import com.heavenhr.interview.model.JobApplication;

public interface ApplicationStatusChangeAction {
	void execute(JobApplication entity, ApplicationStatus status);
}

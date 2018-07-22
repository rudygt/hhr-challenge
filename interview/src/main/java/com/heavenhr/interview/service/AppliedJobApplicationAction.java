package com.heavenhr.interview.service;

import com.heavenhr.interview.model.ApplicationStatus;
import com.heavenhr.interview.model.JobApplication;

public class AppliedJobApplicationAction extends BaseApplicationStatusChangeAction {

	@Override
	public void execute(JobApplication entity, ApplicationStatus status) {
		logger.debug("APPLIED CUSTOM LOGIC");
		super.execute(entity, status);
	}
}

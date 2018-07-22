package com.heavenhr.interview.service;

import com.heavenhr.interview.model.ApplicationStatus;
import com.heavenhr.interview.model.JobApplication;

public class HiredJobApplicationAction extends BaseApplicationStatusChangeAction {

	@Override
	public void execute(JobApplication entity, ApplicationStatus status) {
		logger.debug("CUSTOM LOGIC -> HIRED! <- CUSTOM LOGIC");
		super.execute(entity, status);
	}
}

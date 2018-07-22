package com.heavenhr.interview.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.heavenhr.interview.model.ApplicationStatus;
import com.heavenhr.interview.model.JobApplication;

public class BaseApplicationStatusChangeAction implements ApplicationStatusChangeAction {

	protected static Logger logger = LogManager.getLogger(BaseApplicationStatusChangeAction.class);

	@Override
	public void execute(JobApplication entity, ApplicationStatus status) {
		logger.debug("Application Status Changed " + entity.getId() + " new status -> " + status);
	}
}

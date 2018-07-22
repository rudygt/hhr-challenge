package com.heavenhr.interview.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.heavenhr.interview.model.ApplicationStatus;
import com.heavenhr.interview.model.JobApplication;

@Service
public class ApplicationStatusServiceImpl implements ApplicationStatusService {

	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	private Map<ApplicationStatus, Class<?>> applicationStateChangeActionMap;

	public ApplicationStatusServiceImpl() {
		applicationStateChangeActionMap = new HashMap<ApplicationStatus, Class<?>>();

		applicationStateChangeActionMap.put(ApplicationStatus.APPLIED, AppliedJobApplicationAction.class);
		applicationStateChangeActionMap.put(ApplicationStatus.INVITED, BaseApplicationStatusChangeAction.class);
		applicationStateChangeActionMap.put(ApplicationStatus.REJECTED, BaseApplicationStatusChangeAction.class);
		applicationStateChangeActionMap.put(ApplicationStatus.HIRED, HiredJobApplicationAction.class);
	}

	@Override
	public void handleStatusChange(JobApplication entity, ApplicationStatus oldStatus, ApplicationStatus newStatus) {

		if (applicationStateChangeActionMap.containsKey(newStatus)) {

			ApplicationStatusChangeAction action;
			try {
				action = (ApplicationStatusChangeAction) applicationStateChangeActionMap.get(newStatus).getConstructor()
						.newInstance();

				beanFactory.autowireBean(action);
				
				action.execute(entity, newStatus);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public Map<ApplicationStatus, Class<?>> getApplicationStateChangeActionMap() {
		return applicationStateChangeActionMap;
	}

	public void setApplicationStateChangeActionMap(Map<ApplicationStatus, Class<?>> applicationStateChangeActionMap) {
		this.applicationStateChangeActionMap = applicationStateChangeActionMap;
	}

}

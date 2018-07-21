package com.heavenhr.interview.dto;

import java.util.Date;

public class UpdateJobOffer {
	private String jobTitle;
	private Date startDate;

	public UpdateJobOffer() {
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}

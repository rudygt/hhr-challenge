package com.heavenhr.interview.dto;

import com.heavenhr.interview.model.ApplicationStatus;

public class JobApplicationDTO {

	private Long id;

	private String candidateEmail;

	private String resumeText;

	private ApplicationStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getResumeText() {
		return resumeText;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
}

package com.heavenhr.interview.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

@Entity
public class JobOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String jobTitle;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	//@Transient
	@Formula("(select count(*) from JOB_APPLICATION a where a.JOB_OFFER_ID = id)")
	private Integer numberOfApplications;

	@OneToMany(mappedBy = "jobOffer", fetch = FetchType.LAZY)
	private List<JobApplication> applications;
	
	public JobOffer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getNumberOfApplications() {
		return numberOfApplications;
	}

	public void setNumberOfApplications(Integer numberOfApplications) {
		this.numberOfApplications = numberOfApplications;
	}

	public List<JobApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<JobApplication> applications) {
		this.applications = applications;
	}
}

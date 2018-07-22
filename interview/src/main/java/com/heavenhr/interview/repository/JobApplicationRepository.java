package com.heavenhr.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavenhr.interview.model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

}

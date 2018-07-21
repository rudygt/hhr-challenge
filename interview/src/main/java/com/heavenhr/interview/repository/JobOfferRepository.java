package com.heavenhr.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavenhr.interview.model.JobOffer;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

}

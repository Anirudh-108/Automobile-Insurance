package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.model.ClaimDetails;

public interface ClaimDetailsRepository extends JpaRepository<ClaimDetails, Integer> {

}

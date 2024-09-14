package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.model.CustomerDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Integer> {

}

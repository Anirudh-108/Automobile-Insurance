package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.model.CustomerPolicy;

public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Integer> {

}

package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.model.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {

}

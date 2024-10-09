package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{

}

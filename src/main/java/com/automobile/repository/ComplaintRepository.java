package com.automobile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{
	
	@Query("select c from Complaint c where c.customer.id=?1")
	Page<Complaint> getAllComplaints(int customerId, Pageable pageable);

}

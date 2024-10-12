package com.automobile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.Complain;

public interface ComplaintRepository extends JpaRepository<Complain, Integer>{

	@Query("select c from Complain c where c.customer.id=?1")
	Page<Complain> getAllComplaints(int customerId, Pageable pageable);

}

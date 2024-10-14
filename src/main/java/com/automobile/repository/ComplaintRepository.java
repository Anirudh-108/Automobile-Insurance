package com.automobile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

<<<<<<< HEAD
import com.automobile.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{
	
	@Query("select c from Complaint c where c.customer.id=?1")
	Page<Complaint> getAllComplaints(int customerId, Pageable pageable);
=======
import com.automobile.model.Complain;

public interface ComplaintRepository extends JpaRepository<Complain, Integer>{

	@Query("select c from Complain c where c.customer.id=?1")
	Page<Complain> getAllComplaints(int customerId, Pageable pageable);
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

}

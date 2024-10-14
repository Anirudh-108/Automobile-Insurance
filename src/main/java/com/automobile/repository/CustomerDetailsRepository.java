package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.CustomerDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Integer> {
	@Query("select cd from CustomerDetails cd where cd.customer.id=?1")
	CustomerDetails getCustomerDetailsById(int customerId);
}

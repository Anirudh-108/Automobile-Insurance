package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c JOIN c.userInfo u where u.username=?1")
	Customer getCustomer(String empUsername);
	
	@Query("select c.name from Customer c where c.userInfo.username=?1")
	String getNameByUsername(String username);

	@Query("select c from Customer c where c.userInfo.username=?1")
	Customer getCustomerByUsername(String username);

}
package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.stereotype.Repository;

import com.automobile.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c JOIN c.user u where u.username=?1")
	Customer getCustomer(String customerUsername);

	@Query("select c.name from Customer c where c.user.username=?1")
	String getNameByUsername(String username);

	@Query("select c from Customer c where c.user.username=?1")

	Customer getCustomerByUsername(String username);

}
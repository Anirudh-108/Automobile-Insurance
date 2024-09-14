package com.automobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.model.Customer;
import com.automobile.model.CustomerDetails;
import com.automobile.repository.CustomerDetailsRepository;
import com.automobile.repository.CustomerRepository;

@Service
public class CustomerDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;
	
	public CustomerDetails addCustomerDetails(String empUsername, CustomerDetails customerDetails) {
		Customer customer = customerRepository.getEmployee(empUsername);
		
		customerDetails.setCustomer(customer);
		
		return customerDetailsRepository.save(customerDetails);
	}

}

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

	public CustomerDetails addCustomerDetails(String customerUsername, CustomerDetails customerDetails) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		customerDetails.setCustomer(customer);
		return customerDetailsRepository.save(customerDetails);
	}

	public CustomerDetails updateCustomerDetails(String customerUsername, CustomerDetails newCustomerDetails) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		int customerId = customer.getId();
		CustomerDetails customerDetailsDB = customerDetailsRepository.getCustomerDetailsById(customerId);
		customerDetailsDB.setAadharNumber(newCustomerDetails.getAadharNumber());
		customerDetailsDB.setDrivingLicenceNumber(newCustomerDetails.getDrivingLicenceNumber());
		customerDetailsDB.setPanNumber(newCustomerDetails.getPanNumber());
		customerDetailsDB.setCustomer(customer);
		return customerDetailsRepository.save(customerDetailsDB);
	}

}

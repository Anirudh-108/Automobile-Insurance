package com.automobile.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private Logger logger = LoggerFactory.getLogger(CustomerDetailsService.class);

	public CustomerDetails addCustomerDetails(String customerUsername, CustomerDetails customerDetails) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		customerDetails.setCustomer(customer);

		logger.info("Adding customer details to DB " + customerDetails);
		return customerDetailsRepository.save(customerDetails);
	}

	public CustomerDetails updateCustomerDetails(String customerUsername, CustomerDetails newCustomerDetails) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		int customerId = customer.getId();
		CustomerDetails customerDetailsDB = customerDetailsRepository.getCustomerDetailsById(customerId);
		customerDetailsDB.setAadharCard(newCustomerDetails.getAadharCard());
		customerDetailsDB.setDrivingLicence(newCustomerDetails.getDrivingLicence());
		customerDetailsDB.setPanCard(newCustomerDetails.getPanCard());
		customerDetailsDB.setCustomer(customer);

		logger.info("Updating customer details in DB " + customerDetailsDB);
		return customerDetailsRepository.save(customerDetailsDB);
	}

	public CustomerDetails uploadDocuments(String customerUsername, List<String> fileNamesList) {
		Customer customer = customerRepository.getCustomer(customerUsername);

		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAadharCard(fileNamesList.get(0));
		customerDetails.setDrivingLicence(fileNamesList.get(1));
		customerDetails.setPanCard(fileNamesList.get(2));
		customerDetails.setCustomer(customer);

		logger.info("Adding customer details to DB " + customerDetails);
		return customerDetailsRepository.save(customerDetails);
	}

}

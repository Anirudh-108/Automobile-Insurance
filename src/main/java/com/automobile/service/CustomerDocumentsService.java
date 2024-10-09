package com.automobile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.model.CustomerDocuments;
import com.automobile.repository.CustomerDocumentsRepository;

@Service
public class CustomerDocumentsService {

	@Autowired
	private CustomerDocumentsRepository customerDetailsRepository;

	private Logger logger = LoggerFactory.getLogger(CustomerDocumentsService.class);

	public void addCustomerDocuments(CustomerDocuments customerDocuments) {
		logger.info("Adding customer documents to DB " + customerDocuments);
		customerDetailsRepository.save(customerDocuments);
	}

}

//public CustomerDocuments addCustomerDetails(String customerUsername, CustomerDocuments customerDetails) {
//	Customer customer = customerRepository.getCustomer(customerUsername);
//	customerDetails.setCustomer(customer);
//
//	logger.info("Adding customer details to DB " + customerDetails);
//	return customerDetailsRepository.save(customerDetails);
//}
//
//public CustomerDocuments updateCustomerDetails(String customerUsername, CustomerDocuments newCustomerDetails) {
//	Customer customer = customerRepository.getCustomer(customerUsername);
//	int customerId = customer.getId();
//	CustomerDocuments customerDetailsDB = customerDetailsRepository.getCustomerDetailsById(customerId);
//	customerDetailsDB.setAadharCard(newCustomerDetails.getAadharCard());
//	customerDetailsDB.setDrivingLicence(newCustomerDetails.getDrivingLicence());
//	customerDetailsDB.setPanCard(newCustomerDetails.getPanCard());
//	customerDetailsDB.setCustomer(customer);
//
//	logger.info("Updating customer details in DB " + customerDetailsDB);
//	return customerDetailsRepository.save(customerDetailsDB);
//}
//
//public CustomerDocuments uploadDocuments(String customerUsername, List<String> fileNamesList) {
//	Customer customer = customerRepository.getCustomer(customerUsername);
//
//	CustomerDocuments customerDetails = new CustomerDocuments();
//	customerDetails.setAadharCard(fileNamesList.get(0));
//	customerDetails.setDrivingLicence(fileNamesList.get(1));
//	customerDetails.setPanCard(fileNamesList.get(2));
//	customerDetails.setCustomer(customer);
//
//	logger.info("Adding customer details to DB " + customerDetails);
//	return customerDetailsRepository.save(customerDetails);
//}

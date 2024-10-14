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

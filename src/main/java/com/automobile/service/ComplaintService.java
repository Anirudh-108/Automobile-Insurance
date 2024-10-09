package com.automobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.model.Complaint;
import com.automobile.model.Customer;
import com.automobile.model.Policy;
import com.automobile.repository.ComplaintRepository;
import com.automobile.repository.CustomerRepository;
import com.automobile.repository.PolicyRepository;

@Service
public class ComplaintService {

	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PolicyRepository policyRepository;

	public Complaint addComplaint(int polcyId, Complaint complaint, String customerUsername) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		Policy policy=policyRepository.findById(polcyId).get();
		complaint.setCustomer(customer);
		complaint.setPolicy(policy);
		return complaintRepository.save(complaint);
	}

	public List<Complaint> getAllComplaints() {
		return complaintRepository.findAll();
	}

}

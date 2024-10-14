package com.automobile.service;

import java.util.List;

<<<<<<< HEAD
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.enums.PolicyStatus;
import com.automobile.model.Customer;
import com.automobile.model.Policy;
import com.automobile.repository.CustomerRepository;
import com.automobile.repository.PolicyRepository;
import com.automobile.repository.RenewalPolicyRepository;

@Service
public class RenewPolicyService {

	@Autowired
	private RenewalPolicyRepository renewalPolicyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PolicyRepository policyRepository;

<<<<<<< HEAD
	public List<Policy> getAllRenewalPolicy(String customerUsername) {
		Customer customerDB = customerRepository.getCustomer(customerUsername);
		int customerId = customerDB.getId();
=======
	private Logger logger = LoggerFactory.getLogger(RenewPolicyService.class);

	public List<Policy> getAllRenewalPolicy(String customerUsername) {
		Customer customerDB = customerRepository.getCustomer(customerUsername);
		int customerId = customerDB.getId();

		logger.info("Getting expired policy from DB");
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
		return renewalPolicyRepository.findPolicyByStatus(customerId, PolicyStatus.Expired);
	}

	public Policy renewPolicy(int policyId, String customerUsername) {
		Customer customerDB = customerRepository.getCustomer(customerUsername);
		int customerId = customerDB.getId();
		Policy policy = policyRepository.getPolicyByCustomerAndPolicyId(customerId, policyId);
		policy.setPolicyStatus(PolicyStatus.Active);
<<<<<<< HEAD
=======

		logger.info("Adding updated policy to DB");
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
		return policyRepository.save(policy);
	}

}

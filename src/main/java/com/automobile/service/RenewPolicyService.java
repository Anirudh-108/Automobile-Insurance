package com.automobile.service;

import java.util.List;

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

	public List<Policy> getAllRenewalPolicy(String customerUsername) {
		Customer customerDB = customerRepository.getCustomer(customerUsername);
		int customerId = customerDB.getId();
		return renewalPolicyRepository.findPolicyByStatus(customerId, PolicyStatus.Expired);
	}

	public Policy renewPolicy(int policyId, String customerUsername) {
		Customer customerDB = customerRepository.getCustomer(customerUsername);
		int customerId = customerDB.getId();
		Policy policy = policyRepository.getPolicyByCustomerAndPolicyId(customerId, policyId);
		policy.setPolicyStatus(PolicyStatus.Active);
		return policyRepository.save(policy);
	}

}

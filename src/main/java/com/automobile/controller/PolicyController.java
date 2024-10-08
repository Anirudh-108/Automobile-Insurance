package com.automobile.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.ShowPolicyDto;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Policy;
import com.automobile.model.Vehicle;
import com.automobile.service.PolicyService;

@RestController
@RequestMapping("/policy")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PolicyController {
	@Autowired
	private PolicyService policyService;

	// API for getting policy details according to vehicle details
	@PostMapping("/showPolicy/{policyType}")
	public ShowPolicyDto showPolicy(@RequestBody Vehicle vehicle, @PathVariable String policyType) {
		return policyService.showPolicy(vehicle, policyType);
	}
	
	@PostMapping("/getPolicy/{policyType}")
	public Policy getPolicy(@RequestBody Vehicle vehicle, @PathVariable String policyType) {
		return policyService.getPolicy(vehicle, policyType);
	}

	// API for buying policy according to vehicle details 
	@PostMapping("/buy/{policyType}")
	public CustomerPolicy buyPolicy(@RequestBody Vehicle vehicle, @PathVariable String policyType,
			Principal principal) {
		String customerUsername = principal.getName();
		Policy policy = policyService.getPolicy(vehicle, policyType);
		return policyService.buyPolicy(customerUsername, policy, vehicle);
	}
}

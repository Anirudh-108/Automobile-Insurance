package com.automobile.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.MessageDto;
import com.automobile.model.Policy;
import com.automobile.service.RenewPolicyService;

@RestController
@RequestMapping("/renewal")
<<<<<<< HEAD
=======
@CrossOrigin(origins = { "http://localhost:4200" })
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
public class RenewalController {

	@Autowired
	private RenewPolicyService renewPolicyService;

<<<<<<< HEAD
=======
	// API for getting all expired policies
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
	@GetMapping("/showAll")
	public ResponseEntity<?> getAllRenewalPolicy(Principal principal, MessageDto dto) {
		String customerUsername = principal.getName();
		List<Policy> renewalList = renewPolicyService.getAllRenewalPolicy(customerUsername);
		if (renewalList.isEmpty()) {
			dto.setMsg("No policy available for renewal");
			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.ok(renewalList);
	}

<<<<<<< HEAD
	@PostMapping("/renew/{policyId}")
	public Policy renewPolicy(@PathVariable int policyId,Principal principal) {
		String customerUsername = principal.getName();
		return renewPolicyService.renewPolicy(policyId,customerUsername);
	}
	
	
=======
	// API for renewing a policy
	@PostMapping("/renew/{policyId}")
	public ResponseEntity<?> renewPolicy(@PathVariable int policyId, Principal principal, MessageDto dto) {

//		String policyStatus = policyRepository.findById(policyId).get().getPolicyStatus().toString();
//		if (policyStatus.equals("Active")) {
//			dto.setMsg("This policy is not expired yet, you can't renew it now.");
//			return ResponseEntity.badRequest().body(dto);
//		}
		String customerUsername = principal.getName();
		Policy policy = renewPolicyService.renewPolicy(policyId, customerUsername);
		return ResponseEntity.ok(policy);
	}
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

}

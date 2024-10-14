package com.automobile.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = { "http://localhost:4200" })
public class RenewalController {

	@Autowired
	private RenewPolicyService renewPolicyService;

	// API for getting all expired policies
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

}

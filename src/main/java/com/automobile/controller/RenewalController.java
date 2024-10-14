package com.automobile.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class RenewalController {

	@Autowired
	private RenewPolicyService renewPolicyService;

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

	@PostMapping("/renew/{policyId}")
	public Policy renewPolicy(@PathVariable int policyId,Principal principal) {
		String customerUsername = principal.getName();
		return renewPolicyService.renewPolicy(policyId,customerUsername);
	}
	
	

}

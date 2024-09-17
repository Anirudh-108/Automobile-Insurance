package com.automobile.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.MessageDto;
import com.automobile.exception.CannotClaimPolicyException;
import com.automobile.exception.PolicyNotClaimedException;
import com.automobile.model.ClaimDetails;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.Policy;
import com.automobile.service.ClaimPolicyService;

@RestController
@RequestMapping("/claim")
public class ClaimPolicyController {

	@Autowired
	private ClaimPolicyService claimPolicyService;

	@GetMapping("/showAll")
	public ResponseEntity<?> getAllActivePolicy(Principal principal, MessageDto dto) {
		String customerUsername = principal.getName();
		List<Policy> activeList = claimPolicyService.getAllActivePolicy(customerUsername);
		if (activeList.isEmpty()) {
			dto.setMsg("No active policies available");
			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.ok(activeList);
	}

	@PostMapping("/one/{policyId}")
	public ResponseEntity<?> claimPolicy(@PathVariable int policyId, Principal principal,
			@RequestBody ClaimDetails claimDetails, MessageDto dto) {
		String customerUsername = principal.getName();
		try {
			ClaimPolicy claimPolicy = claimPolicyService.claimPolicy(policyId, customerUsername, claimDetails);
			return ResponseEntity.ok(claimPolicy);
		} catch (CannotClaimPolicyException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

	@GetMapping("/status/{policyId}")
	public ResponseEntity<?> claimPolicyStatus(@PathVariable int policyId, Principal principal, MessageDto dto) {
		String customerUsername = principal.getName();
		String claimStatus;
		try {
			claimStatus = claimPolicyService.claimPolicyStatus(policyId, customerUsername);
			dto.setMsg(claimStatus);
			return ResponseEntity.ok(dto);
		} catch (PolicyNotClaimedException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
}

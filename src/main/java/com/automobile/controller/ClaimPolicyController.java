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
import com.automobile.model.ClaimDocuments;
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
	public ClaimPolicy claimPolicy(@PathVariable int policyId,Principal principal,@RequestBody ClaimDocuments claimDocuments) {
		String customerUsername = principal.getName();
		return claimPolicyService.claimPolicy(policyId,customerUsername,claimDocuments);
	}
}

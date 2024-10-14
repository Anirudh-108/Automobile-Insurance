package com.automobile.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.MessageDto;
import com.automobile.exception.CannotClaimPolicyException;
import com.automobile.exception.InvalidIdException;
import com.automobile.exception.PolicyNotClaimedException;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.ClaimPolicyDto;
import com.automobile.dto.MessageDto;
import com.automobile.exception.CannotClaimPolicyException;

import com.automobile.model.ClaimDetails;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.Policy;
import com.automobile.service.ClaimPolicyService;



@RequestMapping("/claim")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ClaimPolicyController {

	@Autowired
	private ClaimPolicyService claimPolicyService;

	// API for getting all the active policies
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


	// API for claiming a policy by providing Claim documents

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
	
	//Executive
	
	//Fetch details of a claim-policy by claim ID
    @GetMapping("/claim-details/{id}")
    public ResponseEntity<ClaimPolicy> getClaimDetails(@PathVariable int id) throws InvalidIdException {
        ClaimPolicy claimDetails = claimPolicyService.getClaimDetails(id);
        return ResponseEntity.ok(claimDetails);
    }

	@GetMapping("/all-claims")
	public Page<ClaimPolicyDto> getAllClaims(@RequestParam(defaultValue = "0", required = false) Integer page,
			@RequestParam(defaultValue = "100000", required = false) Integer size, Principal principal) {
		String customerUsername = principal.getName();

		Pageable pageable = PageRequest.of(page, size);
		return claimPolicyService.getAllClaims(customerUsername, pageable);
	}

	@GetMapping("/getNumberOfClaimsFiled")
	public MessageDto getNumberOfClaimsFiled(Principal principal, MessageDto dto) {
		String customerUsername = principal.getName();
		long activeNo = claimPolicyService.getNumberOfClaimsFiled(customerUsername);
		dto.setMsg(String.valueOf(activeNo));
		return dto;
	}


}

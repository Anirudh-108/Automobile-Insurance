package com.automobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.CustomerPolicyDto;
import com.automobile.enums.ClaimStatus;
import com.automobile.enums.PolicyRequestStatus;
import com.automobile.exception.InvalidIdException;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.CustomerPolicy;
import com.automobile.service.ExecutiveService;

@RestController
@RequestMapping("/executive")
public class ExecutiveController {
	@Autowired
	private ExecutiveService executiveService;

	// Fetch all policies of a customer by customer ID
    @GetMapping("/policies/{customerId}")
    public ResponseEntity<List<CustomerPolicy>> getCustomerPolicies(@PathVariable int customerId) throws InvalidIdException {
        List<CustomerPolicy> customerPolicies = executiveService.getPoliciesByCustomerId(customerId);
        return ResponseEntity.ok(customerPolicies);
    }
    
    
    
    //fetch policies by policyRequestStatus
  	@GetMapping("/policies/status/{policyRequestStatus}")
    public ResponseEntity<?> getPoliciesByPolicyRequestStatus(@PathVariable PolicyRequestStatus policyRequestStatus) throws InvalidIdException {
  		List<CustomerPolicyDto> policies = executiveService.getPoliciesByPolicyRequestStatus(policyRequestStatus);
          return ResponseEntity.ok(policies);
      }
  	
  	
  	
    //update policy status from CustomerPolicy
  	@PutMapping("/policy/{policyId}/request-status")
  	public ResponseEntity<CustomerPolicy> updatePolicyRequestStatus(@PathVariable int policyId,
  	                                                                @RequestParam PolicyRequestStatus policyRequestStatus,
  	                                                                @RequestParam int executiveId) throws InvalidIdException {
  	    CustomerPolicy updatedPolicy = executiveService.updatePolicyRequestStatus(policyId, policyRequestStatus, executiveId);
  	    return ResponseEntity.ok(updatedPolicy);
  	}
  	
  	
  	//fetch claim by claimStatus
  	@GetMapping("/claim/status")
	public  ResponseEntity<List<ClaimPolicy>> getClaimByClaimStatus(@RequestParam ClaimStatus claimStatus) throws InvalidIdException{
		List<ClaimPolicy> claims= executiveService.getClaimsByClaimStatus(claimStatus);
		return ResponseEntity.ok(claims);
	}
  	
  	
  	//update claimStatus form ClaimPolicy
  	@PutMapping("/policy/{claimpolicyId}/status")
	public ResponseEntity<ClaimPolicy> updateClaimStatus(@PathVariable int claimpolicyId,
	                                                         @RequestParam ClaimStatus claimStatus,
	                                                         @RequestParam int executiveId) throws InvalidIdException {
	    ClaimPolicy updatedPolicy = executiveService.updateClaimStatus(claimpolicyId, claimStatus, executiveId);
	    return ResponseEntity.ok(updatedPolicy);
	}
  	
	
}
	

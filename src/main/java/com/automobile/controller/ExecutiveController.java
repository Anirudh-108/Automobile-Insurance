package com.automobile.controller;

<<<<<<< HEAD
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import com.automobile.dto.MessageDto;
=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import com.automobile.enums.ClaimStatus;
import com.automobile.enums.PolicyRequestStatus;
import com.automobile.exception.InvalidIdException;
import com.automobile.model.ClaimPolicy;
<<<<<<< HEAD
import com.automobile.model.Complaint;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Executive;
import com.automobile.repository.ExecutiveRepository;
import com.automobile.service.ExecutiveService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
=======
import com.automobile.model.CustomerPolicy;
import com.automobile.service.ExecutiveService;

@RestController
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
@RequestMapping("/executive")
public class ExecutiveController {
	@Autowired
	private ExecutiveService executiveService;
<<<<<<< HEAD
	
	@Autowired
	public ExecutiveRepository executiveRepository;
	
	
	
	
	//fetch all policies
	@GetMapping("/policies/all")
	public Page<CustomerPolicy> getAllPolicies(@RequestParam(defaultValue = "0", required = false) Integer page, 
			@RequestParam(defaultValue = "1000", required = false) Integer size){
		
		Pageable pageable =   PageRequest.of(page, size);
		
		return executiveService.getAllPolicies(pageable);
	}

	//fetch all policies of a customer by customer ID
=======

	// Fetch all policies of a customer by customer ID
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
    @GetMapping("/policies/{customerId}")
    public ResponseEntity<List<CustomerPolicy>> getCustomerPolicies(@PathVariable int customerId) throws InvalidIdException {
        List<CustomerPolicy> customerPolicies = executiveService.getPoliciesByCustomerId(customerId);
        return ResponseEntity.ok(customerPolicies);
    }
    
<<<<<<< HEAD
    //API for enums
    @GetMapping("/policy-status")
    public List<PolicyRequestStatus> getPolicyStatus() {
    	
    	return List.of(PolicyRequestStatus.values());
    	
    }
    
    @GetMapping("/claim-status")
    public List<ClaimStatus> getClaimStatus() {
    	
    	return List.of(ClaimStatus.values());
    	
    }
    
  //fetch all policy details by policy ID
    @GetMapping("/policy-details/{policyId}")
    public ResponseEntity<CustomerPolicy> getPolicyDetails(@PathVariable int policyId) throws InvalidIdException {
        CustomerPolicy policyDetails = executiveService.getPolicyDetails(policyId);
        return ResponseEntity.ok(policyDetails);
    }
    
    
    //fetch policies by policyRequestStatus
  	@GetMapping("/policies/status/{policyRequestStatus}")
    public ResponseEntity<?> getPoliciesByPolicyRequestStatus(@PathVariable PolicyRequestStatus policyRequestStatus) throws InvalidIdException {
  		List<CustomerPolicy> policies = executiveService.getPoliciesByPolicyRequestStatus(policyRequestStatus);
          return ResponseEntity.ok(policies);
      }
  	
  	@GetMapping("/claims/all")
	public Page<ClaimPolicy> getAllClaims(@RequestParam(defaultValue = "0", required = false) Integer page, 
			@RequestParam(defaultValue = "1000", required = false) Integer size){
  		
  		Pageable pageable = PageRequest.of(page, size);
		return executiveService.getAllClaims(pageable);
	}

  	//update policy request status from CustomerPolicy
=======
    
    
    //fetch policies by policyRequestStatus
//  	@GetMapping("/policies/status/{policyRequestStatus}")
//    public ResponseEntity<?> getPoliciesByPolicyRequestStatus(@PathVariable PolicyRequestStatus policyRequestStatus) throws InvalidIdException {
//  		List<CustomerPolicyForExecutiveDto> policies = executiveService.getPoliciesByPolicyRequestStatus(policyRequestStatus);
//          return ResponseEntity.ok(policies);
//      }
//  	
  	
  	
    //update policy status from CustomerPolicy
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
  	@PutMapping("/policy/{policyId}/request-status")
  	public ResponseEntity<CustomerPolicy> updatePolicyRequestStatus(@PathVariable int policyId,
  	                                                                @RequestParam PolicyRequestStatus policyRequestStatus,
  	                                                                @RequestParam int executiveId) throws InvalidIdException {
  	    CustomerPolicy updatedPolicy = executiveService.updatePolicyRequestStatus(policyId, policyRequestStatus, executiveId);
  	    return ResponseEntity.ok(updatedPolicy);
  	}
  	
  	
<<<<<<< HEAD

  	
=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
  	//fetch claim by claimStatus
  	@GetMapping("/claim/status")
	public  ResponseEntity<List<ClaimPolicy>> getClaimByClaimStatus(@RequestParam ClaimStatus claimStatus) throws InvalidIdException{
		List<ClaimPolicy> claims= executiveService.getClaimsByClaimStatus(claimStatus);
		return ResponseEntity.ok(claims);
	}
  	
<<<<<<< HEAD
  @GetMapping("/details")
  	public Optional<Executive> getExecutiveDetails(Principal principal) {
  		
  		return executiveService.getExecutiveDetails(principal);
  		
  		
  	}
  	
=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
  	
  	//update claimStatus form ClaimPolicy
  	@PutMapping("/policy/{claimpolicyId}/status")
	public ResponseEntity<ClaimPolicy> updateClaimStatus(@PathVariable int claimpolicyId,
	                                                         @RequestParam ClaimStatus claimStatus,
	                                                         @RequestParam int executiveId) throws InvalidIdException {
	    ClaimPolicy updatedPolicy = executiveService.updateClaimStatus(claimpolicyId, claimStatus, executiveId);
	    return ResponseEntity.ok(updatedPolicy);
	}
  	
<<<<<<< HEAD
    @DeleteMapping("/delete/executive/{eid}")
    public ResponseEntity<MessageDto> deleteExecutive(@PathVariable int eid, MessageDto dto) {
        try {
           executiveService.deleteExecutive(eid);
            dto.setMsg("Executive deleted successfully.");
            return ResponseEntity.ok(dto);
        } catch (InvalidIdException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }
    
    @GetMapping("/viewall-complaints")
    public ResponseEntity<List<Complaint>> getAllComplaints() {
    	 List<Complaint> complaints = executiveService.getAllComplaints();
         return ResponseEntity.ok(complaints);
    
    }
=======
	
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}
	

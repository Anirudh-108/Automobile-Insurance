package com.automobile.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.dto.CustomerPolicyDto;
import com.automobile.enums.ClaimStatus;
import com.automobile.enums.PolicyRequestStatus;
import com.automobile.exception.InvalidIdException;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Executive;
import com.automobile.model.ExecutiveClaim;
import com.automobile.repository.ClaimPolicyRepository;
import com.automobile.repository.CustomerPolicyRepository;
import com.automobile.repository.ExecutiveClaimRepository;
import com.automobile.repository.ExecutiveRepository;

import jakarta.transaction.Transactional;

@Service
public class ExecutiveService {
	
	@Autowired
	private CustomerPolicyRepository customerPolicyRepository;
	
	@Autowired
	private ExecutiveRepository executiveRepository;
	
	@Autowired
	private ClaimPolicyRepository claimPolicyRepository;
	
	@Autowired
	private ExecutiveClaimRepository executiveClaimRepository;

	
	 // Fetch list of policies for a particular customer
    public List<CustomerPolicy> getPoliciesByCustomerId(int customerId) throws InvalidIdException {
        List<CustomerPolicy> customerPolicies = customerPolicyRepository.findByCustomerId(customerId);
        if (customerPolicies.isEmpty()) {
            throw new InvalidIdException("No policies found for customer ID: " + customerId);
        }
        return customerPolicies;
    }


	// fetch policies based on the request status (Approved, Requested, Cancelled)
		 public List<CustomerPolicyDto> getPoliciesByPolicyRequestStatus(PolicyRequestStatus policyRequestStatus) throws InvalidIdException {

		        // Fetch policies by the provided status
		        List<CustomerPolicyDto> policies = customerPolicyRepository.findByPolicyRequestStatus(policyRequestStatus);
		        
		        if (policies.isEmpty()) {
		            throw new InvalidIdException("No policies found with status: " + policyRequestStatus);
		        }
		        return policies;
	}
		 

		// Update the policy request status from CustomerPolicy (Requested to Approved or Cancelled) 
		@Transactional
		public CustomerPolicy updatePolicyRequestStatus(int policyId, PolicyRequestStatus policyRequestStatus, int executiveId) throws InvalidIdException {
		      CustomerPolicy customerPolicy = customerPolicyRepository.findById(policyId)
		              .orElseThrow(() -> new RuntimeException("Policy not found with id: " + policyId));
		        
		      Executive executive = executiveRepository.findById(executiveId)
		              .orElseThrow(() -> new InvalidIdException("Executive not found with id: " + executiveId));
		        
		        customerPolicy.setPolicyRequestStatus(policyRequestStatus);
		        customerPolicy.setUpdatedBy(executive);
		        return customerPolicyRepository.save(customerPolicy);
		    }
		
		public List<ClaimPolicy> getClaimsByClaimStatus(ClaimStatus claimStatus) throws InvalidIdException {
			List<ClaimPolicy> claims= claimPolicyRepository.findByclaimStatus(claimStatus);
			
			if(claims.isEmpty()) {
				throw new InvalidIdException("Claim not found with status " + claimStatus);
			}
			return claims;
		}


		 @Transactional
		    public ClaimPolicy updateClaimStatus(int claimpolicyId, ClaimStatus claimStatus, int executiveId) throws InvalidIdException {
		        // Fetch the claim policy
		        ClaimPolicy claimPolicy = claimPolicyRepository.findById(claimpolicyId)
		                .orElseThrow(() -> new InvalidIdException("Claim policy not found"));
		        
		        Executive executive = executiveRepository.findById(executiveId)
		                .orElseThrow(() -> new InvalidIdException("Executive not found"));
		        
		        // Update the claim status in the ClaimPolicy
		        claimPolicy.setClaimStatus(claimStatus);
		        claimPolicyRepository.save(claimPolicy);

		        // Create a new ExecutiveClaim and store the executive ID along with the updated claim status
		        ExecutiveClaim executiveClaim = new ExecutiveClaim();
		        executiveClaim.setClaimStatus(claimStatus);
		        executiveClaim.setClaimPolicy(claimPolicy);;
		        executiveClaim.setExecutive(executive); // Set the executive who made the update
		        executiveClaim.setUpdateDate(LocalDate.now());
		        
		        // Save the ExecutiveClaim
		        executiveClaimRepository.save(executiveClaim);

		        return claimPolicy;
		    }
}
	

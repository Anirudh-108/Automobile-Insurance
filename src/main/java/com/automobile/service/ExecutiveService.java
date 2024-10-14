package com.automobile.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.automobile.enums.ClaimStatus;
import com.automobile.enums.PolicyRequestStatus;
import com.automobile.exception.InvalidIdException;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.Complaint;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Executive;
import com.automobile.model.ExecutiveClaim;
import com.automobile.model.UserInfo;
import com.automobile.repository.ClaimPolicyRepository;
import com.automobile.repository.ComplaintRepository;
import com.automobile.repository.CustomerPolicyRepository;
import com.automobile.repository.ExecutiveClaimRepository;
import com.automobile.repository.ExecutiveRepository;
import com.automobile.repository.UserRepository;

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
	
	@Autowired
	public ComplaintRepository complaintRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//fetch all policies
	public Page<CustomerPolicy> getAllPolicies(Pageable pageable){
		return customerPolicyRepository.findAll(pageable);
		
	}

	// Fetch list of policies for a particular customer
	public List<CustomerPolicy> getPoliciesByCustomerId(int customerId) throws InvalidIdException {
		List<CustomerPolicy> customerPolicies = customerPolicyRepository.findByCustomerId(customerId);
		if (customerPolicies.isEmpty()) {
			throw new InvalidIdException("No policies found for customer ID: " + customerId);
		}
		return customerPolicies;
	}
	
	// Fetch details of a policy by policy id
	public CustomerPolicy getPolicyDetails(int policyId) throws InvalidIdException {
		CustomerPolicy policyDetails = customerPolicyRepository.findByPolicyId(policyId);
		return policyDetails;
	}

	// fetch policies based on the request status (Approved, Requested, Cancelled)
	public List<CustomerPolicy> getPoliciesByPolicyRequestStatus(PolicyRequestStatus policyRequestStatus)
			throws InvalidIdException {

		// Fetch policies by the provided status
		List<CustomerPolicy> policies = customerPolicyRepository.findByPolicyRequestStatus(policyRequestStatus);

		if (policies.isEmpty()) {
			throw new InvalidIdException("No policies found with status: " + policyRequestStatus);
		}
		return policies;
	}

	//update policyRequestStatus ( Requested to Approved or Cancelled)
	public CustomerPolicy updatePolicyRequestStatus(int policyId, PolicyRequestStatus policyRequestStatus,
			int executiveId) throws InvalidIdException {
		Optional<CustomerPolicy> optional = customerPolicyRepository.findById(policyId);
		if (optional.isEmpty()) {
			throw new InvalidIdException("Policy not found with id: " + policyId);
		}
		Optional<Executive> executive = executiveRepository.findById(executiveId);
		if (executive.isEmpty()) {
			throw new InvalidIdException("Executive not found with id: " + executiveId);
		}

		Executive executiveid = executive.get();
		CustomerPolicy customerPolicy = optional.get();
		customerPolicy.setPolicyRequestStatus(policyRequestStatus);
		customerPolicy.setExecutive(executiveid);
		return customerPolicyRepository.save(customerPolicy);
	}
	
	//fetch all claims
	public Page<ClaimPolicy> getAllClaims(Pageable pageable) {
		return claimPolicyRepository.findAll(pageable);
	}

	// fetch claims by claim status
	public List<ClaimPolicy> getClaimsByClaimStatus(ClaimStatus claimStatus) throws InvalidIdException {
		List<ClaimPolicy> claims = claimPolicyRepository.findByclaimStatus(claimStatus);

		if (claims.isEmpty()) {
			throw new InvalidIdException("Claim not found with status " + claimStatus);
		}
		return claims;
	}

	// update policy claimStatus (Pending to Approved or Denied)
	public ClaimPolicy updateClaimStatus(int claimPolicyId, ClaimStatus claimStatus, int executiveId)
			throws InvalidIdException {
		// Fetch the claim policy
		Optional<ClaimPolicy> optional = claimPolicyRepository.findById(claimPolicyId);
		if (optional.isEmpty()) {
			throw new InvalidIdException("Policy not found with id: " + claimPolicyId);
		}

		Optional<Executive> exeoptional = executiveRepository.findById(executiveId);

		ClaimPolicy claimPolicy = optional.get();
		Executive executive = exeoptional.get();

		claimPolicy.setClaimStatus(claimStatus);
		claimPolicy.setExecutive(executive);
		claimPolicyRepository.save(claimPolicy);

		// Create a new ExecutiveClaim and store the executive ID along with the updated
		// claim status
		ExecutiveClaim executiveClaim = new ExecutiveClaim();
		executiveClaim.setClaimPolicy(claimPolicy);
		executiveClaim.setClaimStatus(claimPolicy.getClaimStatus());
		executiveClaim.setExecutive(executive); // Set the executive who made the update
		executiveClaim.setUpdateDate(LocalDate.now());

		// Save the ExecutiveClaim
		executiveClaimRepository.save(executiveClaim);

		return claimPolicy;
	}
	
    // Method to delete an executive by ID
    public void deleteExecutive(int eid) throws InvalidIdException {
        Optional<Executive> optional = executiveRepository.findById(eid);
        if (optional.isEmpty()) {
            throw new InvalidIdException("Invalid Executive ID: " + eid);
        }
        executiveRepository.deleteById(eid);
    }
    

 // Fetch the details of executive using principal
    public Optional<Executive> getExecutiveDetails(Principal principal) {
       String username = principal.getName();
        
       UserInfo user = userRepository.findByUsername(username).get();
        
       Optional<Executive> executive = executiveRepository.findByUserInfo(user);

        return executive; 
    }
    
    
    public List<Complaint> getAllComplaints() {
	    return complaintRepository.findAll(); 
	}

	
}

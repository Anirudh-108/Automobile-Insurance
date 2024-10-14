package com.automobile.dto;

import java.util.List;

import com.automobile.model.ClaimPolicy;
import com.automobile.model.CustomerPolicy;

public class ExecutivePerformanceDto {
	private int executiveId;
    private String executiveName;
    private int policiesApproved;
    private int policiesCancelled; 
    private int claimsApproved;
    private int claimsDenied;
    private int policiesRequested; 
    private List<CustomerPolicy> customerPolicies; 
    private List<ClaimPolicy> claimPolicies;
    
    
    
    public ExecutivePerformanceDto(int executiveId, String executiveName, int policiesApproved, int policiesCancelled,
			int claimsApproved, int claimsDenied, int policiesRequested, int claimsPending) {
		super();
		this.executiveId = executiveId;
		this.executiveName = executiveName;
		this.policiesApproved = policiesApproved;
		this.policiesCancelled = policiesCancelled;
		this.claimsApproved = claimsApproved;
		this.claimsDenied = claimsDenied;
		this.policiesRequested = policiesRequested;
		this.claimsPending = claimsPending;
	}
    
    
	public int getExecutiveId() {
		return executiveId;
	}
	public void setExecutiveId(int executiveId) {
		this.executiveId = executiveId;
	}
	public String getExecutiveName() {
		return executiveName;
	}
	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}
	public int getPoliciesApproved() {
		return policiesApproved;
	}
	public void setPoliciesApproved(int policiesApproved) {
		this.policiesApproved = policiesApproved;
	}
	public int getPoliciesCancelled() {
		return policiesCancelled;
	}
	public void setPoliciesCancelled(int policiesCancelled) {
		this.policiesCancelled = policiesCancelled;
	}
	public int getClaimsApproved() {
		return claimsApproved;
	}
	public void setClaimsApproved(int claimsApproved) {
		this.claimsApproved = claimsApproved;
	}
	public int getClaimsDenied() {
		return claimsDenied;
	}
	public void setClaimsDenied(int claimsDenied) {
		this.claimsDenied = claimsDenied;
	}
	public int getPoliciesRequested() {
		return policiesRequested;
	}
	public void setPoliciesRequested(int policiesRequested) {
		this.policiesRequested = policiesRequested;
	}
	public int getClaimsPending() {
		return claimsPending;
	}
	public void setClaimsPending(int claimsPending) {
		this.claimsPending = claimsPending;
	}
	private int claimsPending; 
   

   
	
	

    
    
    
}

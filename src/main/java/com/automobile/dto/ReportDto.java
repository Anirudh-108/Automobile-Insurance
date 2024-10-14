package com.automobile.dto;



public class ReportDto {
    private long policyCount;
    private String mostCommonPolicyType;
    private long customerCount;
    private long executiveCount;
    private double highestPremium; 
    

    
   

	public ReportDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportDto(long policyCount, String mostCommonPolicyType, long customerCount, long executiveCount,
			double highestPremium) {
		super();
		this.policyCount = policyCount;
		this.mostCommonPolicyType = mostCommonPolicyType;
		this.customerCount = customerCount;
		this.executiveCount = executiveCount;
		this.highestPremium = highestPremium;
	}

	public long getPolicyCount() {
        return policyCount;
    }

    public void setPolicyCount(long policyCount) {
        this.policyCount = policyCount;
    }

    public String getMostCommonPolicyType() {
        return mostCommonPolicyType;
    }

    public void setMostCommonPolicyType(String mostCommonPolicyType) {
        this.mostCommonPolicyType = mostCommonPolicyType;
    }

    public long getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(long customerCount) {
        this.customerCount = customerCount;
    }

    public long getExecutiveCount() {
        return executiveCount;
    }

    public void setExecutiveCount(long executiveCount) {
        this.executiveCount = executiveCount;
    }

    public double getHighestPremium() {
        return highestPremium;
    }

    public void setHighestPremium(double highestPremium) {
        this.highestPremium = highestPremium;
    }


  
}

package com.automobile.dto;

public class ShowPolicyDto {
	private String policyType;
	private double coverageAmount;
	private double premiumAmount;
	private String termLength;
	private String description;

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public double getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getTermLength() {
		return termLength;
	}

	public void setTermLength(String termLength) {
		this.termLength = termLength;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ShowPolicy [policyType=" + policyType + ", coverageAmount=" + coverageAmount + ", premiumAmount="
				+ premiumAmount + ", termLength=" + termLength + "]";
	}

}

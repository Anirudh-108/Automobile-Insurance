package com.automobile.dto;

public class ClaimPolicyDto {

	private int claimId;
	private double claimAmount;
	private String claimDate;
	private String claimStatus;
	private String policyType;
	private double policyCoverageAmount;
	private String vehicleName;
	private String registrationNo;

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public double getPolicyCoverageAmount() {
		return policyCoverageAmount;
	}

	public void setPolicyCoverageAmount(double policyCoverageAmount) {
		this.policyCoverageAmount = policyCoverageAmount;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	@Override
	public String toString() {
		return "ClaimPolicyDto [claimId=" + claimId + ", claimAmount=" + claimAmount + ", claimDate=" + claimDate
				+ ", claimStatus=" + claimStatus + ", policyType=" + policyType + ", policyCoverageAmount="
				+ policyCoverageAmount + ", vehicleName=" + vehicleName + ", registrationNo=" + registrationNo + "]";
	}

}
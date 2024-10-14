package com.automobile.dto;

import java.time.LocalDate;

public class PolicyDto {

	private int id;
    private String customerName;
    private String policyType;
    private double coverageAmount;
    private double premiumAmount;
    private int termLength;
    private String policyStatus;
    private LocalDate buyingDate;
    private String vehicleDetails;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
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
	public int getTermLength() {
		return termLength;
	}
	public void setTermLength(int termLength) {
		this.termLength = termLength;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public LocalDate getBuyingDate() {
		return buyingDate;
	}
	public void setBuyingDate(LocalDate buyingDate) {
		this.buyingDate = buyingDate;
	}
	public String getVehicleDetails() {
		return vehicleDetails;
	}
	public void setVehicleDetails(String vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}
	@Override
	public String toString() {
		return "PolicyDto [id=" + id + ", customerName=" + customerName + ", policyType=" + policyType
				+ ", coverageAmount=" + coverageAmount + ", premiumAmount=" + premiumAmount + ", termLength="
				+ termLength + ", policyStatus=" + policyStatus + ", buyingDate=" + buyingDate + ", vehicleDetails="
				+ vehicleDetails + "]";
	}
    
}

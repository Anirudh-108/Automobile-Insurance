package com.automobile.dto;

import java.time.LocalDate;

public class CustomerPolicyDto {

	private LocalDate buyingDate;
	private String policyRequestStatus;

	private int policyId;
	private String policyType;
	private double coverageAmount;
	private double premiumAmount;
	private int termLength;
	private String policyStatus;

	private String vehicleType;
	private String manufacturerName;
	private String modelName;
	private String variant;
	private double basePrice;
	private String registrationNo;

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public LocalDate getBuyingDate() {
		return buyingDate;
	}

	public void setBuyingDate(LocalDate buyingDate) {
		this.buyingDate = buyingDate;
	}

	public String getPolicyRequestStatus() {
		return policyRequestStatus;
	}

	public void setPolicyRequestStatus(String policyRequestStatus) {
		this.policyRequestStatus = policyRequestStatus;
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

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	@Override
	public String toString() {
		return "CustomerPolicyDto [buyingDate=" + buyingDate + ", policyRequestStatus=" + policyRequestStatus
				+ ", policyId=" + policyId + ", policyType=" + policyType + ", coverageAmount=" + coverageAmount
				+ ", premiumAmount=" + premiumAmount + ", termLength=" + termLength + ", policyStatus=" + policyStatus
				+ ", vehicleType=" + vehicleType + ", manufacturerName=" + manufacturerName + ", modelName=" + modelName
				+ ", variant=" + variant + ", basePrice=" + basePrice + ", registrationNo=" + registrationNo + "]";
	}

}
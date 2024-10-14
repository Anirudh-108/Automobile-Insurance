package com.automobile.dto;

import java.time.LocalDate;

<<<<<<< HEAD
import com.automobile.enums.PolicyRequestStatus;

public class CustomerPolicyDto {
	
	private int id;
	private int policyId;
	private LocalDate buyingDate;
	private int customerId;
	private String customerName;
	private Integer executiveId;
	private PolicyRequestStatus policyRequestStatus;
	
	public CustomerPolicyDto(int id, int policyId, LocalDate buyingDate, int customerId, String customerName,
			int executiveId, PolicyRequestStatus policyRequestStatus) {
		super();
		this.id = id;
		this.policyId = policyId;
		this.buyingDate = buyingDate;
		this.customerId = customerId;
		this.customerName = customerName;
		this.executiveId = executiveId;
		this.policyRequestStatus = policyRequestStatus;
	}
	public CustomerPolicyDto() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public PolicyRequestStatus getPolicyRequestStatus() {
		return policyRequestStatus;
	}
	public void setPolicyRequestStatus(PolicyRequestStatus policyRequestStatus) {
		this.policyRequestStatus = policyRequestStatus;
	}
	public Integer getExecutiveId() {
		return executiveId;
	}
	public void setExecutiveId(Integer executiveId) {
		this.executiveId = executiveId;
	}
	@Override
	public String toString() {
		return "CustomerPolicyDto [id=" + id + ", policyId=" + policyId + ", buyingDate=" + buyingDate + ", customerId="
				+ customerId + ", customerName=" + customerName + ", executiveId=" + executiveId
				+ ", policyRequestStatus=" + policyRequestStatus + "]";
	}
	
	
	

}
=======
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
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

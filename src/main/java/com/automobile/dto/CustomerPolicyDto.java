package com.automobile.dto;

import java.time.LocalDate;

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

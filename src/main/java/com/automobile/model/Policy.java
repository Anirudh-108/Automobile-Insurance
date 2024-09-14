package com.automobile.model;

import com.automobile.enums.PolicyStatus;
import com.automobile.enums.PolicyType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Enumerated(EnumType.STRING)
	private PolicyType policyType;

	private double coverageAmount;
	private double premiumAmount;
	private int termLength;

	@Enumerated(EnumType.STRING)
	private PolicyStatus policyStatus;
	
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PolicyType getPolicyType() {
		return policyType;
	}

	public void setPolicyType(PolicyType policyType) {
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

	public PolicyStatus getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(PolicyStatus policyStatus) {
		this.policyStatus = policyStatus;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", policyType=" + policyType + ", coverageAmount=" + coverageAmount
				+ ", premiumAmount=" + premiumAmount + ", termLength=" + termLength + "]";
	}

}

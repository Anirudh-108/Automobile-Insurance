package com.automobile.dto;

import com.automobile.enums.PolicyType;
import com.automobile.enums.PolicyStatus;

public class PolicySummaryDto {
    private String customerName;
    private PolicyType policyType;
    private double premiumAmount;
    private double coverageAmount;
    private int termLength;
    private PolicyStatus policyStatus;
    private int policyId;

    // Constructor with the required parameters
    public PolicySummaryDto(String customerName, PolicyType policyType, double premiumAmount,
                            double coverageAmount, int termLength, PolicyStatus policyStatus, int policyId) {
        this.customerName = customerName;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
        this.coverageAmount = coverageAmount;
        this.termLength = termLength;
        this.policyStatus = policyStatus;
        this.policyId = policyId;
    }

    // Getters and Setters

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public PolicyType getPolicyType() {
        return policyType;
    }

    public void setPolicyType(PolicyType policyType) {
        this.policyType = policyType;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
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

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }
}

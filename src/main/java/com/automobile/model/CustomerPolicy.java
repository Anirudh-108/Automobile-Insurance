package com.automobile.model;

import java.time.LocalDate;

import com.automobile.enums.PolicyRequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_policy")
public class CustomerPolicy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private Policy policy;

	private LocalDate buyingDate;

	@Enumerated(EnumType.STRING)
	private PolicyRequestStatus policyRequestStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public LocalDate getBuyingDate() {
		return buyingDate;
	}

	public void setBuyingDate(LocalDate buyingDate) {
		this.buyingDate = buyingDate;
	}

	public PolicyRequestStatus getPolicyRequestStatus() {
		return policyRequestStatus;
	}

	public void setPolicyRequestStatus(PolicyRequestStatus policyRequestStatus) {
		this.policyRequestStatus = policyRequestStatus;
	}

	@Override
	public String toString() {
		return "CustomerPolicy [id=" + id + ", customer=" + customer + ", policy=" + policy + ", buyingDate="
				+ buyingDate + ", policyRequestStatus=" + policyRequestStatus + "]";
	}

}

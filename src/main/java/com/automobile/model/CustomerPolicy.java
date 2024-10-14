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
	
	@ManyToOne
<<<<<<< HEAD
	private Executive executive;
=======
	private Executive updatedBy;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

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

<<<<<<< HEAD
	public Executive getExecutive() {
		return executive;
	}

	public void setExecutive(Executive executive) {
		this.executive = executive;
=======
	public Executive getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Executive updatedBy) {
		this.updatedBy = updatedBy;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
	}

	@Override
	public String toString() {
		return "CustomerPolicy [id=" + id + ", customer=" + customer + ", policy=" + policy + ", buyingDate="
<<<<<<< HEAD
				+ buyingDate + ", policyRequestStatus=" + policyRequestStatus + ", executive=" + executive + "]";
	}

	
=======
				+ buyingDate + ", policyRequestStatus=" + policyRequestStatus + ", updatedBy=" + updatedBy + "]";
	}

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

	


}

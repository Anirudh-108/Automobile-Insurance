package com.automobile.model;

import java.time.LocalDate;

import com.automobile.enums.ClaimStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class ClaimPolicy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private double claimAmount;
	private LocalDate claimDate;

	@Enumerated(EnumType.STRING)
	private ClaimStatus claimStatus;

	@OneToOne
<<<<<<< HEAD
	private CustomerPolicy customerPolicy;

	@OneToOne
	private ClaimDetails claimDocuments;
	
	@ManyToOne
    @JoinColumn(name = "executive_id") 
    private Executive executive;
=======
	private Customer customer;

	@OneToOne
	private Policy policy;

	@OneToOne
	private ClaimDetails claimDocuments;

	@ManyToOne
	@JoinColumn(name = "executive_id")
	private Executive executive;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public LocalDate getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
	}

<<<<<<< HEAD
	public CustomerPolicy getCustomerPolicy() {
		return customerPolicy;
	}

	public void setCustomerPolicy(CustomerPolicy customerPolicy) {
		this.customerPolicy = customerPolicy;
=======
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
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
	}

	public ClaimDetails getClaimDocuments() {
		return claimDocuments;
	}

	public void setClaimDocuments(ClaimDetails claimDocuments) {
		this.claimDocuments = claimDocuments;
	}

	public Executive getExecutive() {
		return executive;
	}

	public void setExecutive(Executive executive) {
		this.executive = executive;
	}

	@Override
	public String toString() {
		return "ClaimPolicy [id=" + id + ", claimAmount=" + claimAmount + ", claimDate=" + claimDate + ", claimStatus="
<<<<<<< HEAD
				+ claimStatus + ", customerPolicy=" + customerPolicy + ", claimDocuments=" + claimDocuments
				+ ", executive=" + executive + "]";
	}

	

=======
				+ claimStatus + ", customer=" + customer + ", policy=" + policy + ", claimDocuments=" + claimDocuments
				+ ", executive=" + executive + "]";
	}

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}

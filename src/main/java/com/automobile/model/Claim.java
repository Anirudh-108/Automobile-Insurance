package com.automobile.model;

import java.time.LocalDate;

import com.automobile.enums.ClaimStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Claim {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private double claimAmount;
	private LocalDate claimDate;

	@Enumerated(EnumType.STRING)
	private ClaimStatus claimStatus;

	@OneToOne
	private CustomerPolicy customerPolicy;

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

	public CustomerPolicy getCustomerPolicy() {
		return customerPolicy;
	}

	public void setCustomerPolicy(CustomerPolicy customerPolicy) {
		this.customerPolicy = customerPolicy;
	}
}

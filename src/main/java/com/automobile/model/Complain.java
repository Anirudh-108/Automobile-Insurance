package com.automobile.model;

import com.automobile.enums.ComplaintStatus;
import com.automobile.enums.ComplaintType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Complain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Enumerated(EnumType.STRING)
	private ComplaintType complaintType;

	@Enumerated(EnumType.STRING)
	private ComplaintStatus complaintStatus;

	@Column(length = 1000)
	private String description;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private Policy policy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ComplaintType getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(ComplaintType complaintType) {
		this.complaintType = complaintType;
	}

	public ComplaintStatus getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(ComplaintStatus complaintStatus) {
		this.complaintStatus = complaintStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", complaintType=" + complaintType + ", complaintStatus=" + complaintStatus
				+ ", description=" + description + ", customer=" + customer + ", policy=" + policy + "]";
	}

}

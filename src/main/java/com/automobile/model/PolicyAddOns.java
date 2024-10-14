package com.automobile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PolicyAddOns {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
<<<<<<< HEAD
	
	@ManyToOne
	private Policy policy;
	
	@ManyToOne
	private AddOns addOns;
=======

	@ManyToOne
	private Policy policy;

	@ManyToOne
	private AddOns addOns;

	@Override
	public String toString() {
		return "PolicyAddOns [id=" + id + ", policy=" + policy + ", addOns=" + addOns + "]";
	}

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}

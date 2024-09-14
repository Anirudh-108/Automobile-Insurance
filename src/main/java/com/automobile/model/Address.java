package com.automobile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String streetDetails;
	private String city;
	private String state;
	private String country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreetDetails() {
		return streetDetails;
	}

	public void setStreetDetails(String streetDetails) {
		this.streetDetails = streetDetails;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", streetDetails=" + streetDetails + ", city=" + city + ", state=" + state
				+ ", country=" + country + "]";
	}

}

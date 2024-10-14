package com.automobile.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String email;

	private String contact;

	@OneToOne
<<<<<<< HEAD
	private UserInfo userInfo;
=======
	private UserInfo user;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

	@ManyToOne
	private Address address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

<<<<<<< HEAD
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
=======
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + ", userInfo="
				+ userInfo + ", address=" + address + "]";
	}

	

=======
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + ", user=" + user
				+ ", address=" + address + "]";
	}

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}
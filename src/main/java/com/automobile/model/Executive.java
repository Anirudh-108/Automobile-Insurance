package com.automobile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Executive {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;
	    
	    private String name;
	    
	    private int age;
	    
	    private String gender;
	    
	    private String department;
	    
	    private String email;
	    
	    private String contactNumber;
	    
	    private String city;
	    
	    private double salary;
	    
	    @OneToOne
	    private UserInfo userInfo;

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

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getContactNumber() {
			return contactNumber;
		}

		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		public UserInfo getUserInfo() {
			return userInfo;
		}

		public void setUserInfo(UserInfo userInfo) {
			this.userInfo = userInfo;
		}

		@Override
		public String toString() {
			return "Executive [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", department="
					+ department + ", email=" + email + ", contactNumber=" + contactNumber + ", city=" + city
					+ ", salary=" + salary + ", userInfo=" + userInfo + "]";
		}
	    
	    
}

package com.automobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.model.Customer;
import com.automobile.model.Vehicle;
import com.automobile.repository.CustomerRepository;
import com.automobile.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	public Vehicle getVehicleByRegistrationNo(String registrationNo, String customerUsername) {
		int custId = customerService.getCustomerByUsername(customerUsername).getId();
		return vehicleRepository.getVehicleByRegistrationNo(custId, registrationNo);
	}

	public Vehicle addVehicle(String customerUsername, Vehicle vehicle) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		vehicle.setCustomer(customer);
		return vehicleRepository.save(vehicle);
	}

}

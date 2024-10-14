package com.automobile.service;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.model.Vehicle;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.model.Customer;
import com.automobile.model.Vehicle;
import com.automobile.repository.CustomerRepository;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import com.automobile.repository.VehicleRepository;

@Service
public class VehicleService {
<<<<<<< HEAD
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public Vehicle getVehicleByModelName(String modelName, String customerUsername) {
		
		int custId = customerService.getCustomerByUsername(customerUsername).getId();
		return vehicleRepository.getVehicleByModelName(custId, modelName);
	}

	public Optional<Vehicle> getVehicleById(int vehicleId) {
		
		return vehicleRepository.findById(vehicleId);
	}


=======

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

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}

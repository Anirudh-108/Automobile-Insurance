package com.automobile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.model.Vehicle;
import com.automobile.repository.VehicleRepository;

@Service
public class VehicleService {
	
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


}

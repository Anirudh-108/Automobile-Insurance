package com.automobile.service;

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

	public Vehicle getVehicleByManufacturerName(String manufacturerName, String customerUsername) {
		int custId = customerService.getCustomerByUsername(customerUsername).getId();
		return vehicleRepository.getVehicleByManufacturerName(custId, manufacturerName);
	}

}

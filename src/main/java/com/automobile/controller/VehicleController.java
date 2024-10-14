package com.automobile.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.model.Vehicle;
import com.automobile.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = { "http://localhost:4200" })
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;

	@PostMapping("/add")
	public Vehicle addVehicle(@RequestBody Vehicle vehicle, Principal principal) {
		String customerUsername = principal.getName();
		return vehicleService.addVehicle(customerUsername,vehicle);
	}

}

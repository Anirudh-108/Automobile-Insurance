package com.automobile.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.model.CustomerDetails;
import com.automobile.service.CustomerDetailsService;

@RestController
@RequestMapping("/customerDetails")
public class CustomerDetailsController {

	@Autowired
	private CustomerDetailsService customerDetailsService;

	@PostMapping("/add")
	public CustomerDetails addCustomerDetails(@RequestBody CustomerDetails customerDetails, Principal principal) {
		String empUsername = principal.getName();
		return customerDetailsService.addCustomerDetails(empUsername, customerDetails);
	}
}

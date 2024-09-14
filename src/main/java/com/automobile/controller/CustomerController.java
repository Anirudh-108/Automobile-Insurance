package com.automobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.MessageDto;
import com.automobile.exception.InvalidIdException;
import com.automobile.model.Customer;
import com.automobile.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@GetMapping("/all")
	public List<Customer> getAllEmployee() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/one/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable int customerId, MessageDto dto) {
		try {
			Customer customer = customerService.getCustomerById(customerId);
			return ResponseEntity.ok(customer);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

	@PutMapping("/update/{customerId}")
	public ResponseEntity<?> updateCustomer(@PathVariable int customerId, @RequestBody Customer newCustomer,
			MessageDto dto) {
		try {
			Customer customer = customerService.updateCustomer(customerId, newCustomer);
			return ResponseEntity.ok(customer);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

}
package com.automobile.controller;

import java.security.Principal;
<<<<<<< HEAD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.MessageDto;
import com.automobile.exception.InvalidIdException;
import com.automobile.model.Customer;
import com.automobile.service.CustomerService;

@RestController
<<<<<<< HEAD
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequestMapping("/customer")
=======
@RequestMapping("/customer")
@CrossOrigin(origins = { "http://localhost:4200" })
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@GetMapping("/all")
<<<<<<< HEAD
	public Page<Customer> getAllCustomers(@RequestParam(defaultValue = "0", required = false) Integer page, 
			@RequestParam(defaultValue = "1000", required = false) Integer size ) {
		
		Pageable pageable =   PageRequest.of(page, size);
		
		return customerService.getAllCustomers(pageable);
=======
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
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

	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody Customer newCustomer, Principal principal) {
		String customerUsername = principal.getName();
		return customerService.updateCustomer(customerUsername, newCustomer);
	}

<<<<<<< HEAD
=======
	@GetMapping("/getName/{username}")
	public MessageDto getNameByUsername(@PathVariable String username, MessageDto dto) {
		String name = customerService.getNameByUsername(username);
		dto.setMsg(name);
		return dto;
	}
	
	@GetMapping("/getCustomer/{username}")
	public Customer getCustomerByUsername(@PathVariable String username) {
		return customerService.getCustomerByUsername(username);
	}
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}
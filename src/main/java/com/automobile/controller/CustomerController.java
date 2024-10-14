package com.automobile.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.MessageDto;
import com.automobile.exception.InvalidIdException;
import com.automobile.model.Customer;
import com.automobile.service.CustomerService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@GetMapping("/all")
	public Page<Customer> getAllCustomers(@RequestParam(defaultValue = "0", required = false) Integer page, 
			@RequestParam(defaultValue = "1000", required = false) Integer size ) {
		
		Pageable pageable =   PageRequest.of(page, size);
		
		return customerService.getAllCustomers(pageable);
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

}
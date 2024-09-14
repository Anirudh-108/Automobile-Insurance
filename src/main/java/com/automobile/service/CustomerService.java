package com.automobile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.automobile.exception.InvalidIdException;
import com.automobile.model.Address;
import com.automobile.model.Customer;
import com.automobile.model.UserInfo;
import com.automobile.repository.AddressRepository;
import com.automobile.repository.CustomerRepository;
import com.automobile.repository.UserRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AddressRepository addressRepository;

	public Customer addCustomer(Customer customer) {
		UserInfo userInfo = customer.getUser();
		userInfo.setRole("ROLE_CUSTOMER");
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfo = userRepository.save(userInfo);
		customer.setUser(userInfo);

		Address address = customer.getAddress();
		address = addressRepository.save(address);
		customer.setAddress(address);

		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(int customerId) throws InvalidIdException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isEmpty())
			throw new InvalidIdException("Given Customer Id is Invalid");
		return optional.get();
	}

	public Customer updateCustomer(int customerId, Customer newCustomer) throws InvalidIdException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isEmpty())
			throw new InvalidIdException("Given Customer Id is Invalid");
		Customer customerDB = optional.get();
		Address addressDB = customerDB.getAddress();

		customerDB.setName(newCustomer.getName());
		customerDB.setEmail(newCustomer.getEmail());
		customerDB.setContact(newCustomer.getContact());

		addressDB.setStreetDetails(newCustomer.getAddress().getStreetDetails());
		addressDB.setCity(newCustomer.getAddress().getCity());
		addressDB.setState(newCustomer.getAddress().getState());
		addressDB.setCountry(newCustomer.getAddress().getCountry());

		addressDB = addressRepository.save(addressDB);
		customerDB.setAddress(addressDB);

		return customerRepository.save(customerDB);
	}

}
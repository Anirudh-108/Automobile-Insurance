package com.automobile.service;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
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

<<<<<<< HEAD
	public Customer addCustomer(Customer customer) {
		UserInfo userInfo = customer.getUserInfo();
		userInfo.setRole("ROLE_CUSTOMER");
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfo = userRepository.save(userInfo);
		customer.setUserInfo(userInfo);
=======
	private Logger logger = LoggerFactory.getLogger(CustomerService.class);

	public Customer addCustomer(Customer customer) {
		UserInfo userInfo = customer.getUser();
		userInfo.setRole("ROLE_CUSTOMER");
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfo = userRepository.save(userInfo);
		customer.setUser(userInfo);
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

		Address address = customer.getAddress();
		address = addressRepository.save(address);
		customer.setAddress(address);

<<<<<<< HEAD
		return customerRepository.save(customer);
	}

	public Page<Customer> getAllCustomers(Pageable pageable) {
		return customerRepository.findAll(pageable);
=======
		logger.info("Adding customer to DB");
		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		logger.info("Getting all customers from DB");
		return customerRepository.findAll();
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
	}

	public Customer getCustomerById(int customerId) throws InvalidIdException {
		Optional<Customer> optional = customerRepository.findById(customerId);
<<<<<<< HEAD
		if (optional.isEmpty())
			throw new InvalidIdException("Given Customer Id is Invalid");
=======
		if (optional.isEmpty()) {
			logger.error("Invalid customer ID found in request, Exception thrown...");
			throw new InvalidIdException("Given Customer Id is Invalid");
		}
		logger.info("Getting customer by ID from DB");
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
		return optional.get();
	}

	public Customer updateCustomer(String customerUsername, Customer newCustomer) {
		Customer customerDB = customerRepository.getCustomer(customerUsername);
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

<<<<<<< HEAD
		return customerRepository.save(customerDB);
	}
	
=======
		logger.info("Adding update customer to DB");
		return customerRepository.save(customerDB);
	}

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
	public String getNameByUsername(String username) {
		return customerRepository.getNameByUsername(username);
	}

	public Customer getCustomerByUsername(String username) {
		return customerRepository.getCustomerByUsername(username);
	}
<<<<<<< HEAD

=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}
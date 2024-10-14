package com.automobile.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.automobile.model.Customer;
import com.automobile.model.CustomerDocuments;
import com.automobile.service.CustomerDocumentsService;
import com.automobile.service.CustomerService;

@RestController
@RequestMapping("/customerDocuments")
@CrossOrigin(origins = { "http://localhost:4200" })
public class CustomerDocumentsController {
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerDocumentsService customerDocumentsService;

	@PostMapping("/upload")
	public void addCustomerDocuments(@RequestParam MultipartFile file, Principal principal) {
		String customerUsername = principal.getName();
		Customer customer = customerService.getCustomerByUsername(customerUsername);

		String location = "E:/Angular-App/public/CustomerDocuments";

		CustomerDocuments customerDocuments = new CustomerDocuments();

		try {
			Files.copy(file.getInputStream(), Path.of(location, file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			customerDocuments.setName(file.getOriginalFilename());
			customerDocuments.setPath(Path.of(location, file.getOriginalFilename()).toString());
			customerDocuments.setCustomer(customer);
			customerDocumentsService.addCustomerDocuments(customerDocuments);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

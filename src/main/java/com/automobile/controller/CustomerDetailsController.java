package com.automobile.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.automobile.model.Customer;
import com.automobile.model.CustomerDetails;
import com.automobile.service.CustomerDetailsService;

import jakarta.persistence.criteria.Path;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/customerDetails")
public class CustomerDetailsController {

	@Autowired
	private CustomerDetailsService customerDetailsService;

	@PostMapping("/add")
	public CustomerDetails addCustomerDetails(@RequestBody CustomerDetails customerDetails, Principal principal) {
		String customerUsername = principal.getName();
		return customerDetailsService.addCustomerDetails(customerUsername, customerDetails);
	}

	@PutMapping("/update")
	public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails newCustomerDetails, Principal principal) {
		String customerUsername = principal.getName();
		return customerDetailsService.updateCustomerDetails(customerUsername, newCustomerDetails);
	}
	
	@PostMapping("/upload/documents")
	public CustomerDetails uploadDocuments(@RequestBody MultipartFile[] files, Principal principal) throws IOException {
		List<String> fileNamesList = new ArrayList<>();
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			String filePathAndName = "C:/Users/LENOVO/Downloads/autoshield-app/public/CustomerDetails"
					+ fileName;
			FileOutputStream fos = new FileOutputStream(filePathAndName);
			InputStream is = file.getInputStream();
			byte[] byt = new byte[is.available()];
			is.read(byt);
			fos.write(byt);
			fos.close();
			fileNamesList.add(filePathAndName); 
		}
		String customerUsername = principal.getName();
		return customerDetailsService.uploadAadddharCard(customerUsername, fileNamesList);
	}
	
/*	@PostMapping("/upload")
	public void addCustomerDocuments(@RequestParam MultipartFile file, Principal principal) {
		String customerUsername = principal.getName();
		Customer customer = customerService.getCustomerByUsername(customerUsername);

		String location = "D:/@Coding/Hexaware Role Based Training/Angular Apps/automobile-insurance/public/customer-documents";

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
	}*/
}

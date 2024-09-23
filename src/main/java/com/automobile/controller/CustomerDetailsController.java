package com.automobile.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.automobile.model.CustomerDetails;
import com.automobile.service.CustomerDetailsService;

@RestController
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
			String filePathAndName = "D:/@Coding/Hexaware Role Based Training/Spring Codes/AutomobileInsurance/src/main/resources/static/CustomerDetails/"
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
}

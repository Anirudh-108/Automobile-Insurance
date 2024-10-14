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
<<<<<<< HEAD
	
=======

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerDocumentsService customerDocumentsService;

	@PostMapping("/upload")
	public void addCustomerDocuments(@RequestParam MultipartFile file, Principal principal) {
		String customerUsername = principal.getName();
		Customer customer = customerService.getCustomerByUsername(customerUsername);

<<<<<<< HEAD
		String location = "E:/Angular-App/public/CustomerDocuments";
=======
		String location = "D:/@Coding/Hexaware Role Based Training/Angular Apps/automobile-insurance/public/customer-documents";
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

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
<<<<<<< HEAD
=======

//	@PutMapping("/update")
//	public CustomerDocuments updateCustomerDetails(@RequestBody CustomerDocuments newCustomerDetails,
//			Principal principal) {
//		String customerUsername = principal.getName();
//		return customerDetailsService.updateCustomerDetails(customerUsername, newCustomerDetails);
//	}

//	@PostMapping("/add")
//	public CustomerDetails addCustomerDetails(@RequestBody CustomerDetails customerDetails, Principal principal) {
//		String customerUsername = principal.getName();
//		return customerDetailsService.addCustomerDetails(customerUsername, customerDetails);
//	}

//	@PostMapping("/upload/documents")
//	public CustomerDetails uploadDocuments(@RequestBody MultipartFile[] files, Principal principal) throws IOException {
//		List<String> fileNamesList = new ArrayList<>();
//
//		for (MultipartFile file : files) {
//			String fileName = file.getOriginalFilename();
//			String filePathAndName = "D:/@Coding/Hexaware Role Based Training/Spring Codes/AutomobileInsurance/src/main/resources/static/CustomerDetails/"
//					+ fileName;
//			FileOutputStream fos = new FileOutputStream(filePathAndName);
//
//			InputStream is = file.getInputStream();
//			byte[] byt = new byte[is.available()];
//			is.read(byt);
//			fos.write(byt);
//			fos.close();
//
//			fileNamesList.add(filePathAndName); 
//		}
//
//		String customerUsername = principal.getName();
//		return customerDetailsService.uploadDocuments(customerUsername, fileNamesList);
//
//	}
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

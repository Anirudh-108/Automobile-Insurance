package com.automobile.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.automobile.model.Vehicle;
import com.automobile.model.VehicleDocuments;
import com.automobile.service.VehicleDocumentsService;
import com.automobile.service.VehicleService;

@RestController
@RequestMapping("/vehicleDocuments")
@CrossOrigin(origins = { "http://localhost:4200" })
public class VehicleDocumentsController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private VehicleDocumentsService vehicleDocumentsService;

	@PostMapping("/upload/{modelName}")
	public void addVehicleDocuments(@PathVariable String modelName, @RequestParam MultipartFile file,
			Principal principal) {
		String customerUsername = principal.getName();
		Vehicle vehicle = vehicleService.getVehicleByModelName(modelName, customerUsername);

		String location = "D:/@Coding/Hexaware Role Based Training/Angular Apps/automobile-insurance/public/vehicle-documents";

		VehicleDocuments vehicleDocuments = new VehicleDocuments();

		try {
			Files.copy(file.getInputStream(), Path.of(location, file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			vehicleDocuments.setName(file.getOriginalFilename());
			vehicleDocuments.setPath(Path.of(location, file.getOriginalFilename()).toString());
			vehicleDocuments.setVehicle(vehicle);
			vehicleDocumentsService.addVehicleDocuments(vehicleDocuments);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

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

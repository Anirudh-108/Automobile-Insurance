package com.automobile.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

		String location = "E:/Angular-App/public/VehicleDocuments";

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
	
	 // API to get document by vehicle id
    @GetMapping("/vehicle/{vehicleId}/documents")
    public ResponseEntity<List<VehicleDocuments>> getVehicleDocumentsByVehicleId(@PathVariable int vehicleId) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle.isPresent()) {
            List<VehicleDocuments> documents = vehicleDocumentsService.getVehicleDocumentsByVehicle(vehicle.get());
            return ResponseEntity.ok(documents);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    

}

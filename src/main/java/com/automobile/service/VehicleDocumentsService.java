package com.automobile.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.model.Vehicle;
import com.automobile.model.VehicleDocuments;
import com.automobile.repository.VehicleDocumentsRepository;

@Service
public class VehicleDocumentsService {
	
	@Autowired
	private VehicleDocumentsRepository vehicleDocumentsRepository;
	
	private Logger logger = LoggerFactory.getLogger(VehicleDocumentsService.class);
	
	public void addVehicleDocuments(VehicleDocuments vehicleDocuments) {
		logger.info("Adding customer documents to DB " + vehicleDocuments);
		vehicleDocumentsRepository.save(vehicleDocuments);
	}

	public List<VehicleDocuments> getVehicleDocumentsByVehicle(Vehicle vehicle) {
		
		return vehicleDocumentsRepository.findByVehicle(vehicle);
	}

}

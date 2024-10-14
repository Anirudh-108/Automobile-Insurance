package com.automobile.service;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.automobile.model.Vehicle;
=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import com.automobile.model.VehicleDocuments;
import com.automobile.repository.VehicleDocumentsRepository;

@Service
public class VehicleDocumentsService {
<<<<<<< HEAD
	
	@Autowired
	private VehicleDocumentsRepository vehicleDocumentsRepository;
	
	private Logger logger = LoggerFactory.getLogger(VehicleDocumentsService.class);
	
=======

	@Autowired
	private VehicleDocumentsRepository vehicleDocumentsRepository;

	private Logger logger = LoggerFactory.getLogger(VehicleDocumentsService.class);

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
	public void addVehicleDocuments(VehicleDocuments vehicleDocuments) {
		logger.info("Adding customer documents to DB " + vehicleDocuments);
		vehicleDocumentsRepository.save(vehicleDocuments);
	}

<<<<<<< HEAD
	public List<VehicleDocuments> getVehicleDocumentsByVehicle(Vehicle vehicle) {
		
		return vehicleDocumentsRepository.findByVehicle(vehicle);
	}

=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}

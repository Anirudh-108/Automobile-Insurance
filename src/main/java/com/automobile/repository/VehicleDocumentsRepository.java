package com.automobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.Vehicle;
import com.automobile.model.VehicleDocuments;

public interface VehicleDocumentsRepository extends JpaRepository<VehicleDocuments, Integer>{
	
	@Query("select vd from VehicleDocuments vd where vd.name = ?1 ORDER BY vd.id LIMIT 1")
	VehicleDocuments getVehicleDocumentsByName(String vehicleDocumentName);

	List<VehicleDocuments> findByVehicle(Vehicle vehicle);

}

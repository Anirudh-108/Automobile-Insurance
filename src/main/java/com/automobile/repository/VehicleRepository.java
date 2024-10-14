package com.automobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	@Query("select v from Vehicle v JOIN v.customer c where c.id=?1 AND v.registrationNo=?2")
	Vehicle getVehicleByRegistrationNo(int custId, String registrationNo);

<<<<<<< HEAD
	@Query("select v from Vehicle v JOIN v.customer c where c.id=?1 AND v.modelName=?2")
	Vehicle getVehicleByModelName(int custId, String modelName);

	@Query("select v from Vehicle v JOIN v.customer c where c.id=?1")
	List<Vehicle> getAllVehicles(int custId);
	
	List<Vehicle> findByCustomerId(int customerId);
=======
	@Query("select v from Vehicle v JOIN v.customer c where c.id=?1")
	List<Vehicle> getAllVehicles(int custId);

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}

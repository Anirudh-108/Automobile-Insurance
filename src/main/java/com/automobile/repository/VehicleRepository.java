package com.automobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	@Query("select v from Vehicle v JOIN v.customer c where c.id=?1 AND v.registrationNo=?2")
	Vehicle getVehicleByRegistrationNo(int custId, String registrationNo);

	@Query("select v from Vehicle v JOIN v.customer c where c.id=?1 AND v.modelName=?2")
	Vehicle getVehicleByModelName(int custId, String modelName);

	@Query("select v from Vehicle v JOIN v.customer c where c.id=?1")
	List<Vehicle> getAllVehicles(int custId);

}

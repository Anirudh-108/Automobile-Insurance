package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	@Query("select v from Vehicle v JOIN v.customer c where c.id=?1")
	Vehicle getVehicleByCustomerId(int custId);

}

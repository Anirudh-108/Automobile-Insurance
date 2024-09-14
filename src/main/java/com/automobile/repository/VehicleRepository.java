package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}

package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.model.VehicleDocuments;

public interface VehicleDocumentsRepository extends JpaRepository<VehicleDocuments, Integer> {

}

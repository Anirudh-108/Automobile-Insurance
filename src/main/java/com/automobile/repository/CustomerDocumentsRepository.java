package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.model.CustomerDocuments;

public interface CustomerDocumentsRepository extends JpaRepository<CustomerDocuments, Integer> {
	
}

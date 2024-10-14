package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}

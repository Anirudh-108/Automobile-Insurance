package com.automobile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.Executive;
import com.automobile.model.UserInfo;

public interface ExecutiveRepository extends JpaRepository<Executive, Integer>{

	@Query("SELECT e FROM Executive e WHERE e.name = :name")
	Executive findByName(String name);
	
	 Optional<Executive> findByUserInfo(UserInfo user);
}



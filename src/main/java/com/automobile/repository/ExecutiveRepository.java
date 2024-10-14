package com.automobile.repository;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.Executive;
import com.automobile.model.UserInfo;

public interface ExecutiveRepository extends JpaRepository<Executive, Integer>{

	@Query("SELECT e FROM Executive e WHERE e.name = :name")
	Executive findByName(String name);
	
	 Optional<Executive> findByUserInfo(UserInfo user);

=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.automobile.model.Executive;

public interface ExecutiveRepository extends JpaRepository<Executive, Integer>{

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}

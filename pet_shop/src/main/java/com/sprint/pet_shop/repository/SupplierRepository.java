package com.sprint.pet_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier , Long> {

	boolean existsByEmail(String email);
	
	 @Query("SELECT s FROM Supplier s JOIN s.pets p WHERE p.name = :petName")
	    List<Supplier> findSuppliersByPetName(@Param("petName") String petName);


}

package com.sprint.pet_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.Supplier;


//  Repository for managing our vendor and supplier data.
//  Helps us find which supplier provides a specific pet or food item.
public interface SupplierRepository extends JpaRepository<Supplier , Long> {

	// Checks if a supplier already exists using their email address.
	boolean existsByEmail(String email);

	@Query("SELECT s FROM Supplier s ORDER BY s.supplierId ASC")
	List<Supplier> findAllSorted();
	
	
	 @Query("SELECT s FROM Supplier s JOIN s.pets p WHERE p.name = :petName")
	    List<Supplier> findSuppliersByPetName(@Param("petName") String petName);


}

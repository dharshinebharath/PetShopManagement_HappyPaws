package com.sprint.pet_shop.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.pet_shop.entity.GroomingServices;

//  Handles database operations for our Grooming Services.
//  Allows us to easily save new services or search for existing ones by price range.

@Repository
public interface GroomingServicesRepository extends JpaRepository <GroomingServices, Long> {

	// Checks if a service with the same name already exists
	boolean existsByName(String name);
	

	// Finds grooming services that fit within a customer's budget.
	@Query("SELECT g FROM GroomingServices g WHERE g.price BETWEEN :min AND :max")
	List<GroomingServices> findServicesByPriceRange(
	            @Param("min") BigDecimal min,
	            @Param("max") BigDecimal max);


	//  Retrieves all services, sorted by their unique ID.
	@Query("SELECT g FROM GroomingServices g ORDER BY g.serviceId ASC")
	List<GroomingServices> findAllSorted();
	

}

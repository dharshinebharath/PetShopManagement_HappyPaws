package com.sprint.pet_shop.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.Vaccinations;

//  Manages database access for the vaccinations and medical treatments we offer.

public interface VaccinationsRepository extends JpaRepository<Vaccinations, Long> {

	// Checks if a vaccination with the same name already exists
	boolean existsByName(String name);

	// Finds vaccinations within a specified price range
	@Query("SELECT v FROM Vaccinations v WHERE v.price BETWEEN :min AND :max")
	List<Vaccinations> findByPriceRange(@Param("min") BigDecimal min,
			@Param("max") BigDecimal max);

	// Retrieves all vaccinations, sorted by their unique ID
	@Query("SELECT v FROM Vaccinations v ORDER BY v.vaccinationId ASC")
	List<Vaccinations> findAllSorted();

}

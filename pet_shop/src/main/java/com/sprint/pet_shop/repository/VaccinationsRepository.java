package com.sprint.pet_shop.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.GroomingServices;
import com.sprint.pet_shop.entity.Vaccinations;

public interface VaccinationsRepository extends JpaRepository<Vaccinations, Long> {

	boolean existsByName(String name);

	@Query("SELECT v FROM Vaccinations v WHERE v.price BETWEEN :min AND :max")
	List<Vaccinations> findByPriceRange(@Param("min") BigDecimal min,
			@Param("max") BigDecimal max);

	@Query("SELECT v FROM Vaccinations v ORDER BY v.vaccinationId ASC")
	List<Vaccinations> findAllSorted();

}

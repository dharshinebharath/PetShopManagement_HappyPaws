package com.sprint.pet_shop.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.pet_shop.dto.responseDto.GroomingServicesResponseDTO;
import com.sprint.pet_shop.entity.GroomingServices;

@Repository
public interface GroomingServicesRepository extends JpaRepository <GroomingServices, Long> {


	boolean existsByName(String name);
	 @Query("SELECT g FROM GroomingServices g WHERE g.price BETWEEN :min AND :max")
	    List<GroomingServices> findServicesByPriceRange(
	            @Param("min") BigDecimal min,
	            @Param("max") BigDecimal max);

	@Query("SELECT g FROM GroomingServices g ORDER BY g.serviceId ASC")
	List<GroomingServices> findAllSorted();
	

}

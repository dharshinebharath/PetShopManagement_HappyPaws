package com.sprint.pet_shop.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.GroomingServices;
import com.sprint.pet_shop.entity.Pets;

public interface PetsRepository extends JpaRepository<Pets, Long> {

	// 🔹 Get pets handled by employee
    @Query("SELECT p FROM Pets p JOIN p.employees e WHERE e.employeeId = :empId")
    List<Pets> findPetsByEmployeeId(@Param("empId") Long empId);
    
    // 🔍 1. Filter by Category

    List<Pets> findByCategory_CategoryId(Long categoryId);

    // 🔍 2. Filter by Breed
    List<Pets> findByBreedIgnoreCase(String breed);

    @Query("SELECT p FROM Pets p ORDER BY p.pet_id ASC")
    List<Pets> findAllSorted();    // 🔍 3. Filter by Price Range
    List<Pets> findByPriceBetween(BigDecimal min, BigDecimal max);

}

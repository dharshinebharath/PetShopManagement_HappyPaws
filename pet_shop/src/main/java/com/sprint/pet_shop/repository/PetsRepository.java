package com.sprint.pet_shop.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.Pets;

// The main repository for accessing pet data in our database.
// Supports advanced searches like finding pets assigned to specific employees or filtering by breed/price.
public interface PetsRepository extends JpaRepository<Pets, Long> {

    @Query("SELECT p FROM Pets p JOIN p.employees e WHERE e.employeeId = :empId")
    List<Pets> findPetsByEmployeeId(@Param("empId") Long empId);
    

    List<Pets> findByCategory_CategoryId(Long categoryId);

    List<Pets> findByBreedContainingIgnoreCase(String breed);

    @Query("SELECT p FROM Pets p ORDER BY p.pet_id ASC")
    List<Pets> findAllSorted();
    List<Pets> findByPriceBetween(BigDecimal min, BigDecimal max);

}


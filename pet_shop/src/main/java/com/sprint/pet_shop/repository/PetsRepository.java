package com.sprint.pet_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.Pets;

public interface PetsRepository extends JpaRepository<Pets, Long> {

	// 🔹 Get pets handled by employee
    @Query("SELECT p FROM Pets p JOIN p.employees e WHERE e.employeeId = :empId")
    List<Pets> findPetsByEmployeeId(@Param("empId") Long empId);
}

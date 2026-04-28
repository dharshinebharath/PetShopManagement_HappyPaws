package com.sprint.pet_shop.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.Employees;

/**
 * Repository interface for managing Employee records.
 * Handles fetching staff data, checking emails, and filtering by roles (positions).
 */
public interface EmployeesRepository extends JpaRepository<Employees , Long>{


	 
	//  Fetches all employees that hold a specific job title or position (e.g., 'Manager', 'Groomer').
	 @Query("SELECT e FROM Employees e WHERE e.position = :position")
	    List<Employees> findEmployeesByPosition(@Param("position") String position);
	 

		// Checks if an email already exists in the database.
	     boolean existsByEmail(String email);


		// Fetches all employees who were hired after a specific date.
	     List<Employees> findByHireDateAfter(LocalDate date);
		
		// Fetches all employees sorted by their ID in ascending order.
	     List<Employees> findAllByOrderByEmployeeIdAsc();

}

// This repository handles database access for employees repository.
package com.sprint.pet_shop.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees , Long>{


	 
	 @Query("SELECT e FROM Employees e WHERE e.position = :position")
	    List<Employees> findEmployeesByPosition(@Param("position") String position);
	 

	     boolean existsByEmail(String email);


	     List<Employees> findByHireDateAfter(LocalDate date);
	     List<Employees> findAllByOrderByEmployeeIdAsc();

}

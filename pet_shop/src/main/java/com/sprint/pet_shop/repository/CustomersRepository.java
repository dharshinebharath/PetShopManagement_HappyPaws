package com.sprint.pet_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.pet_shop.entity.Customers;

public interface CustomersRepository extends JpaRepository<Customers,Long> {
	
	@Query("SELECT c FROM Customers c")
	List<Customers> getAll();
}

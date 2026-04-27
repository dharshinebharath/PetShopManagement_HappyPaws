package com.sprint.pet_shop.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.Addresses;

/**
 * Repository for managing Address data in the database.
 * This interface gives us all the standard database operations (like save, find, delete)
 * without needing to write boilerplate SQL queries.
 */
public interface AddressesRepository extends JpaRepository<Addresses,Long> {

	/**
	 * Finds all addresses located in a specific city.
	 */
	@Query("SELECT a FROM Addresses a WHERE a.city = :city")
	List<Addresses> getAddressesByCity(@Param("city") String city);

	/**
	 * Retrieves the complete list of addresses, ordered nicely by their ID.
	 */
	@Query("SELECT a FROM Addresses a ORDER BY a.addressId ASC")
	List<Addresses> findAllSorted();

	
}

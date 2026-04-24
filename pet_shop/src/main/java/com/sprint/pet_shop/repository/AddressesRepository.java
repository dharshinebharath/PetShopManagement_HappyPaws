// This repository handles database access for addresses repository.
package com.sprint.pet_shop.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.Addresses;

public interface AddressesRepository extends JpaRepository<Addresses,Long> {

	@Query("SELECT a FROM Addresses a WHERE a.city = :city")
	List<Addresses> getAddressesByCity(@Param("city") String city);

	@Query("SELECT a FROM Addresses a ORDER BY a.addressId ASC")
	List<Addresses> findAllSorted();

	
}

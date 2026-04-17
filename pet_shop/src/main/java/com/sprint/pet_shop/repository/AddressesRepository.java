package com.sprint.pet_shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.pet_shop.entity.Addresses;

public interface AddressesRepository extends JpaRepository<Addresses,Long> {


}

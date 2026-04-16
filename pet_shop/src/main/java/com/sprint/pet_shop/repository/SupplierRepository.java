package com.sprint.pet_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.pet_shop.entity.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier , Long> {

}

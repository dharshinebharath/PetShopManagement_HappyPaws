package com.sprint.pet_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.pet_shop.entity.Customers;

public interface CustomersRepository extends JpaRepository<Customers,Long> {

}

package com.sprint.pet_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.pet_shop.entity.Pets;

public interface PetsRepository extends JpaRepository<Pets, Long> {

}

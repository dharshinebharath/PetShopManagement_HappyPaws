package com.sprint.pet_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.pet_shop.entity.Vaccinations;

public interface VaccinationsRepository extends JpaRepository<Vaccinations, Long> {

	boolean existsByName(String name);

}

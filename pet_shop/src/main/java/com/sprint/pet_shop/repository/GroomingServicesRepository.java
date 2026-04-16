package com.sprint.pet_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.pet_shop.entity.GroomingServices;

@Repository
public interface GroomingServicesRepository extends JpaRepository <GroomingServices, Long> {

}

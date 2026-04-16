package com.sprint.pet_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.pet_shop.entity.PetFood;

public interface PetFoodRepository extends JpaRepository<PetFood, Long> {

	 @Query("SELECT p FROM PetFood p")
	List<PetFood> getAllPetFood();
	 
	 

}

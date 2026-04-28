package com.sprint.pet_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.pet_shop.entity.PetFood;

//  Repository interface for managing our Pet Food inventory.
//  Gives us tools to check if certain brands exist and pull our full inventory list.
public interface PetFoodRepository extends JpaRepository<PetFood, Long> {

	 @Query("SELECT p FROM PetFood p")
	List<PetFood> getAllPetFood();

	@Query("SELECT p FROM PetFood p ORDER BY p.foodId ASC")
	List<PetFood> findAllSorted();

	 boolean existsByNameAndBrand(String name, String brand);
	 
	   

}

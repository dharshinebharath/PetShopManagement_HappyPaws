package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.entity.PetFood;

public interface PetFoodInterface {

	List<PetFood> saveAllPetFood(List<PetFood> petFoods);

	List<PetFood> getAllPetFood();

	PetFood getPetFoodById(long id);

	void deletePetFoodById(long id);

	PetFood updatePetFood(long id, PetFood petFood);

}

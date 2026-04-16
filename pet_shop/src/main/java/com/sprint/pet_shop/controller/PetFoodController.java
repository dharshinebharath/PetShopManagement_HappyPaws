package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sprint.pet_shop.entity.PetFood;
import com.sprint.pet_shop.service.PetFoodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/food")
public class PetFoodController {

	@Autowired
	private PetFoodService petFoodService;
	
	@PostMapping
	public List<PetFood> addPetFood(@RequestBody List<PetFood> petFoods)
	{
		return petFoodService.saveAllPetFood(petFoods);
	}
	
	@GetMapping
	public List<PetFood> getPetFood()
	{
		return petFoodService.getAllPetFood();
	}
	
	@GetMapping("/{foodId}")
	public PetFood getPetFoodById(@PathVariable long foodId)
	{
		return petFoodService.getPetFoodById(foodId);
	}
	
	@DeleteMapping("/{foodId}")
	public String deletePetFoodById(@PathVariable long foodId)
	{
		petFoodService.deletePetFoodById(foodId);
		return "Food Deleted Successfully";
	}
	
	@PutMapping("/{foodId}")
	public PetFood updatePetFood(
	        @PathVariable long foodId,
	        @Valid @RequestBody PetFood petFood) {

	    return petFoodService.updatePetFood(foodId, petFood);
	}
}

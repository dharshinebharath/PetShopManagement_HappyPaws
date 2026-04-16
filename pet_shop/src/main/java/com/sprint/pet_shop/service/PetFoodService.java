package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.entity.PetFood;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.PetFoodRepository;
import com.sprint.pet_shop.service.interfaces.PetFoodInterface;

@Service
public class PetFoodService implements PetFoodInterface {

	@Autowired
	private PetFoodRepository petFoodRepository;
	
	
	@Override
	public List<PetFood> saveAllPetFood(@RequestBody List<PetFood> petFoods)
	{
		for (PetFood food : petFoods) {
			
			

	        if (food.getQuantity() < 0) {
	            throw new InvalidDataException("Quantity cannot be negative");
	        }

	        if (food.getPrice().doubleValue() < 0) {
	            throw new InvalidDataException("Price must be positive");
	        }

	        if (food.getName() == null || food.getName().isBlank()) {
	            throw new InvalidDataException("Food name cannot be empty");
	        }
	        
	        if (petFoodRepository.existsByNameAndBrand(
	                food.getName(), food.getBrand())) {

	            throw new DuplicateResourceException(
	                "Food already exists: " + food.getName() + " (" + food.getBrand() + ")");
	        }
	    }

		return petFoodRepository.saveAll(petFoods);
	}
	
	@Override
	public List<PetFood> getAllPetFood()
	{
		return petFoodRepository.getAllPetFood();
	}

	@Override
	public PetFood getPetFoodById(long id) {
		return petFoodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Food Not Found with id:" +id));

	}
	
	@Override
	public void deletePetFoodById(long id) {

		PetFood existing=petFoodRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Food Not Found with id:"+id));
		petFoodRepository.delete(existing);
	}
	
	@Override
	public PetFood updatePetFood(long id, PetFood petFood) {

	    PetFood existing = petFoodRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Food Not Found with id:"+ id));

	    if (petFood.getQuantity() < 0) {
	        throw new InvalidDataException("Quantity cannot be negative");
	    }

	    if (petFood.getPrice().doubleValue() < 0) {
	        throw new InvalidDataException("Price must be positive");
	    }
	    existing.setName(petFood.getName());
	    existing.setBrand(petFood.getBrand());
	    existing.setType(petFood.getType());
	    existing.setQuantity(petFood.getQuantity());
	    existing.setPrice(petFood.getPrice());

	    return petFoodRepository.save(existing);
	}
	
	
}

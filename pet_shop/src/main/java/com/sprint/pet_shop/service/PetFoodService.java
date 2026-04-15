package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.entity.PetFood;
import com.sprint.pet_shop.repository.PetFoodRepository;
import com.sprint.pet_shop.service.interfaces.PetFoodInterface;

@Service
public class PetFoodService implements PetFoodInterface {

	@Autowired
	private PetFoodRepository petFoodRepository;
	
	
	@Override
	public List<PetFood> saveAllPetFood(@RequestBody List<PetFood> petFoods)
	{
		return petFoodRepository.saveAll(petFoods);
	}
	
	@Override
	public List<PetFood> getAllPetFood()
	{
		return petFoodRepository.getAllPetFood();
	}

	@Override
	public PetFood getPetFoodById(long id) {
		return petFoodRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Not Found"));

	}
	
	@Override
	public void deletePetFoodById(long id) {

		PetFood existing=petFoodRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Not Found"));
		petFoodRepository.delete(existing);
	}
	
	@Override
	public PetFood updatePetFood(long id, PetFood petFood) {

	    PetFood existing = petFoodRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Not Found"));

	    existing.setName(petFood.getName());
	    existing.setBrand(petFood.getBrand());
	    existing.setType(petFood.getType());
	    existing.setQuantity(petFood.getQuantity());
	    existing.setPrice(petFood.getPrice());

	    return petFoodRepository.save(existing);
	}
	
	
}

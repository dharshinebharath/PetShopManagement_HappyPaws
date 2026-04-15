package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.entity.PetFood;
import com.sprint.pet_shop.repository.PetFoodRepository;

@Service
public class PetFoodService {

	@Autowired
	private PetFoodRepository petFoodRepository;
	
	
	public List<PetFood> saveAllPetFood(@RequestBody List<PetFood> petFoods)
	{
		return petFoodRepository.saveAll(petFoods);
	}
	
	public List<PetFood> getAllPetFood()
	{
		return petFoodRepository.findAll();
	}

	public PetFood getPetFoodById(long id) {
		return petFoodRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Not Found"));

	}

	public void deletePetFoodById(long id) {

		PetFood existing=petFoodRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Not Found"));
		petFoodRepository.delete(existing);
	}
}

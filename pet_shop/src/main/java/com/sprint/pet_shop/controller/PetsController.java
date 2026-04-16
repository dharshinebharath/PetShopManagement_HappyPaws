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

import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.service.PetsService;

@RestController
@RequestMapping("/api/v1/pets")
public class PetsController {

	@Autowired
	private PetsService petsService;
	
	@GetMapping
	public List<Pets> getAllPets()
	{
		return petsService.getAllPets();
	}
	@PostMapping("/saveAll")
	public List<Pets> addAllPets(@RequestBody List<Pets> pets)
	{
		return petsService.addAllPets(pets);
	}
	@GetMapping("/{id}")
	public Pets getPetsById (@PathVariable long id)
	{
		return petsService.getPetById(id);
	}
	@PutMapping("/{id}")
	public Pets updatePets(
	        @PathVariable long id,
	         @RequestBody Pets pets) {

	    return petsService.updatePets(id, pets);
	}
	@DeleteMapping("/{id}")
	public void deletePet(@PathVariable long id)
	{
		petsService.deletePets(id);
	}
}

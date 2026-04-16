package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.repository.PetsRepository;

@Service
public class PetsService {
	
	@Autowired
	private PetsRepository petsRepository;
	
	public List<Pets> getAllPets()
	{
		return petsRepository.findAll();
	}
	public List<Pets> addAllPets(List<Pets> pets)
	{
		return petsRepository.saveAll(pets);
	}
	public Pets getPetById(long id)
	{
		return petsRepository.findById(id).orElse(null);
	}
	public Pets updatePets(long id, Pets pets) {

	    Pets existing = petsRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Pet Not Found with id:"+ id));

	    existing.setName(pets.getName());
	    existing.setBreed(pets.getBreed());
	    existing.setAge(pets.getAge());
	    existing.setDescription(pets.getDescription());
	    existing.setPrice(pets.getPrice());
	    existing.setImage_url(pets.getImage_url());

	    return petsRepository.save(existing);
	}
	public void deletePets(long id)
	{
		petsRepository.deleteById(id);
	}
}

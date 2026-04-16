package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.PetsRepository;
import com.sprint.pet_shop.service.interfaces.PetsInterface;

@Service
public class PetsService implements PetsInterface {
	
	@Autowired
	private PetsRepository petsRepository;
	
	@Override
	public List<Pets> getAllPets()
	{
		return petsRepository.findAll();
	}
	
	@Override
	public List<Pets> addAllPets(List<Pets> pets)
	{
		 for (Pets pet : pets) {

	            if (pet.getName() == null || pet.getName().isBlank()) {
	                throw new InvalidDataException("Pet name cannot be empty");
	            }

	            if (pet.getPrice() == null || pet.getPrice().doubleValue() < 0) {
	                throw new InvalidDataException("Price must be positive");
	            }

	            if (pet.getAge() == null || pet.getAge() < 0) {
	                throw new InvalidDataException("Age cannot be negative");
	            }
	        }
		return petsRepository.saveAll(pets);
	}
	
	@Override
	public Pets getPetById(long id)
	{
		return petsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));
    }
	
	@Override
	public Pets updatePets(long id, Pets pets) {

		 Pets existing = petsRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));

	        if (pets.getName() == null || pets.getName().isBlank()) {
	            throw new InvalidDataException("Pet name cannot be empty");
	        }

	        if (pets.getPrice() == null || pets.getPrice().doubleValue() < 0) {
	            throw new InvalidDataException("Price must be positive");
	        }

	        if (pets.getAge() == null || pets.getAge() < 0) {
	            throw new InvalidDataException("Age cannot be negative");
	        }
	    existing.setName(pets.getName());
	    existing.setBreed(pets.getBreed());
	    existing.setAge(pets.getAge());
	    existing.setDescription(pets.getDescription());
	    existing.setPrice(pets.getPrice());
	    existing.setImage_url(pets.getImage_url());

	    return petsRepository.save(existing);
	}
	
	@Override
	public void deletePets(long id)
	{
		Pets existing = petsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));

        petsRepository.delete(existing);
	}
}

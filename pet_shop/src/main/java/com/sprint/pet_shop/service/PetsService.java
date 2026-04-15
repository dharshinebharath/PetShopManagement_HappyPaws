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
	
//	public List<Pets> getAllPets()
//	{
//		return petsRepository.findAll();
//	}
	public List<Pets> addAllPets(List<Pets> pets)
	{
		return petsRepository.saveAll(pets);
	}
//	public Pets getPetById(long id)
//	{
//		return petsRepository.findById(id).orElse(null);
//	}
//	
//	public void deletePets(long id)
//	{
//		petsRepository.deleteById(id);
//	}
}

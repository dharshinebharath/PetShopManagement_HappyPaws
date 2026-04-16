package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.repository.PetCategoriesRepository;

@Service
public class PetCategoriesService {
	
	@Autowired
	private PetCategoriesRepository petCategoryRepository;
	
	public List<PetCategories> getAllCategories()
	{
		return petCategoryRepository.findAll();
	}
	
	public List<PetCategories>addAll(List<PetCategories> categories)
	{
	    return petCategoryRepository.saveAll(categories);
	}
	public PetCategories getCategoryById(long id)
	{
		return petCategoryRepository.findById(id).orElse(null);
	}
	public PetCategories updatePetCategories(long id, PetCategories petCategories) {

	    PetCategories existing = petCategoryRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Pet Category Not Found with id:"+ id));

	    existing.setName(petCategories.getName());

	    return petCategoryRepository.save(existing);
	}
	public void deleteCategory(long id)
	{
		petCategoryRepository.deleteById(id);
	}
}

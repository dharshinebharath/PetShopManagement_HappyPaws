package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.PetCategoriesRepository;

@Service
public class PetCategoriesService implements PetCategoriesInterface{
	
	@Autowired
	private PetCategoriesRepository petCategoryRepository;
	
	@Override
	public List<PetCategories> getAllCategories()
	{
		return petCategoryRepository.findAll();
	}
	
	@Override
	public List<PetCategories>addAll(List<PetCategories> categories)
	{
		 for (PetCategories category : categories) {

	            if (category.getName() == null || category.getName().isBlank()) {
	                throw new InvalidDataException("Category name cannot be empty");
	            }
	        }
	    return petCategoryRepository.saveAll(categories);
	}
	
	@Override
	public PetCategories getCategoryById(long id)
	{
		 return petCategoryRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
	    }
	
	@Override
	public PetCategories updatePetCategories(long id, PetCategories petCategories) {

		PetCategories existing = petCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (petCategories.getName() == null || petCategories.getName().isBlank()) {
            throw new InvalidDataException("Category name cannot be empty");
        }
	    existing.setName(petCategories.getName());

	    return petCategoryRepository.save(existing);
	}
	
	@Override
	public void deleteCategory(long id)
	{
		PetCategories existing = petCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        petCategoryRepository.delete(existing);
	}
}

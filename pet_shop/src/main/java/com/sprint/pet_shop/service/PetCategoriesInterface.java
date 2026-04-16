package com.sprint.pet_shop.service;

import java.util.List;

import com.sprint.pet_shop.entity.PetCategories;

public interface PetCategoriesInterface {

	List<PetCategories> getAllCategories();

	List<PetCategories> addAll(List<PetCategories> categories);

	PetCategories getCategoryById(long id);

	PetCategories updatePetCategories(long id, PetCategories petCategories);

	void deleteCategory(long id);

}

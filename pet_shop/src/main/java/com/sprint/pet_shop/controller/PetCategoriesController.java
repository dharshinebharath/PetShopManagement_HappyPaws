package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.service.PetCategoriesService;

@RestController
@RequestMapping("api/v1/categories")
public class PetCategoriesController {

	@Autowired
	private PetCategoriesService petCategoryService;
	
//	@GetMapping
//	public List<PetCategories> getAllCategories()
//	{
//		return petCategoryService.getAllCategories();
//	}
	@PostMapping("/save-all")
	public List<PetCategories> addAll(@RequestBody List<PetCategories> categories) {
	    return petCategoryService.addAll(categories);
	}
//	@GetMapping("{id}")
//	public PetCategories getCategoryById(@PathVariable long id)
//	{
//		return petCategoryService.getCategoryById(id);
//	}
//	@DeleteMapping("/{id}")
//	public void deleteCategory(@PathVariable long id)
//	{
//		petCategoryService.deleteCategory(id);
//	}
}

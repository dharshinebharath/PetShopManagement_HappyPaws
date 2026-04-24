// This service contains the main business flow for pet categories service.
package com.sprint.pet_shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.PetCategoriesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetCategoriesResponseDTO;
import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.PetCategoriesRepository;
import com.sprint.pet_shop.service.interfaces.PetCategoriesInterface;

@Service
public class PetCategoriesService implements PetCategoriesInterface{
	
	@Autowired
	private PetCategoriesRepository petCategoryRepository;
	
	 private PetCategoriesResponseDTO toDto(PetCategories entity) {

	        PetCategoriesResponseDTO dto = new PetCategoriesResponseDTO();

	        dto.setCategory_id(entity.getCategory_id());
	        dto.setName(entity.getName());

	        return dto;
	    }
	
	
	@Override
	 public ApiResponse<List<PetCategoriesResponseDTO>> addAll(
	            List<PetCategoriesRequestDTO> dtos) {

	        List<PetCategories> entities = new ArrayList<>();

	        for (PetCategoriesRequestDTO dto : dtos) {

	            if (dto.getName() == null || dto.getName().isBlank()) {
	                throw new InvalidDataException("Category name cannot be empty");
	            }

	            PetCategories entity = new PetCategories();
	            entity.setName(dto.getName());

	            entities.add(entity);
	        }

	        List<PetCategoriesResponseDTO> responseList =
	        		petCategoryRepository.saveAll(entities)
	                        .stream()
	                        .map(this::toDto)
	                        .toList();

	        ApiResponse<List<PetCategoriesResponseDTO>> response = new ApiResponse<>();
	        response.setMessage("Categories saved successfully");
	        response.setSuccess(true);
	        response.setData(responseList);

	        return response;
	    }


	@Override
    public ApiResponse<List<PetCategoriesResponseDTO>> getAllCategories() {

        List<PetCategoriesResponseDTO> data =
                petCategoryRepository.findAllSorted()
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<PetCategoriesResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Fetched all categories");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }
	
	@Override
	 public ApiResponse<PetCategoriesResponseDTO> getCategoryById(long id) {

        PetCategories entity = petCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        ApiResponse<PetCategoriesResponseDTO> response = new ApiResponse<>();
        response.setMessage("Category fetched successfully");
        response.setSuccess(true);
        response.setData(toDto(entity));

        return response;
    }
	
	@Override
	public ApiResponse<PetCategoriesResponseDTO> updateCategory(
            long id, PetCategoriesRequestDTO dto) {

        PetCategories existing = petCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new InvalidDataException("Category name cannot be empty");
        }

        existing.setName(dto.getName());

        PetCategories updated = petCategoryRepository.save(existing);

        ApiResponse<PetCategoriesResponseDTO> response = new ApiResponse<>();
        response.setMessage("Updated successfully");
        response.setSuccess(true);
        response.setData(toDto(updated));

        return response;
    }
	
	@Override
	public ApiResponse<String> deleteCategory(long id) {

        PetCategories existing = petCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        petCategoryRepository.delete(existing);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Deleted successfully");
        response.setSuccess(true);
        response.setData("Deleted category id: " + id);

        return response;
    }
}

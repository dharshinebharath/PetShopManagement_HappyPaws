package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.dto.requestDto.PetCategoriesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetCategoriesResponseDTO;

/**
 * Service interface for handling Pet Categories.
 * Provides the outline for retrieving, updating, and removing categories 
 * like "Reptiles" or "Dogs".
 */
public interface PetCategoriesInterface {

	ApiResponse<List<PetCategoriesResponseDTO>> addAll(
            List<PetCategoriesRequestDTO> dtos);

    ApiResponse<List<PetCategoriesResponseDTO>> getAllCategories();

    ApiResponse<PetCategoriesResponseDTO> getCategoryById(long id);

    ApiResponse<PetCategoriesResponseDTO> updateCategory(
            long id, PetCategoriesRequestDTO dto);

    ApiResponse<String> deleteCategory(long id);
}

// This service contains the main business flow for pet categories interface.
package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.dto.requestDto.PetCategoriesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetCategoriesResponseDTO;
import com.sprint.pet_shop.entity.PetCategories;

public interface PetCategoriesInterface {

	ApiResponse<List<PetCategoriesResponseDTO>> addAll(
            List<PetCategoriesRequestDTO> dtos);

    ApiResponse<List<PetCategoriesResponseDTO>> getAllCategories();

    ApiResponse<PetCategoriesResponseDTO> getCategoryById(long id);

    ApiResponse<PetCategoriesResponseDTO> updateCategory(
            long id, PetCategoriesRequestDTO dto);

    ApiResponse<String> deleteCategory(long id);
}

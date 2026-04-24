// This service contains the main business flow for pet food interface.
package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.dto.requestDto.PetFoodRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetFoodResponseDTO;

public interface PetFoodInterface {

    ApiResponse<List<PetFoodResponseDTO>> saveAllPetFood(List<PetFoodRequestDTO> dtos);

    ApiResponse<List<PetFoodResponseDTO>> getAllPetFood();

    ApiResponse<PetFoodResponseDTO> getPetFoodById(long id);

    ApiResponse<PetFoodResponseDTO> updatePetFood(long id, PetFoodRequestDTO dto);

    ApiResponse<String> deletePetFoodById(long id);
}

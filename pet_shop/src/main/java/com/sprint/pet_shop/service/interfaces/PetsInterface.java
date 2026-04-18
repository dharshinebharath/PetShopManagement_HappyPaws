package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.dto.requestDto.PetsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetsResponseDTO;
import com.sprint.pet_shop.entity.Pets;

public interface PetsInterface {

	  // CREATE
    ApiResponse<List<PetsResponseDTO>> addAllPets(List<PetsRequestDTO> dtos);

    // GET ALL
    ApiResponse<List<PetsResponseDTO>> getAllPets();

    // GET BY ID
    ApiResponse<PetsResponseDTO> getPetById(long id);

    // UPDATE
    ApiResponse<PetsResponseDTO> updatePets(long id, PetsRequestDTO dto);

    // DELETE
    ApiResponse<String> deletePets(long id);
    
    ApiResponse<List<PetsResponseDTO>> getPetsByEmployee(Long employeeId);
}

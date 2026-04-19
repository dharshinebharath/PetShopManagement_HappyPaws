package com.sprint.pet_shop.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.sprint.pet_shop.dto.requestDto.PetsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.EmployeesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.GroomingServicesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.PetsResponseDTO;
import com.sprint.pet_shop.dto.responseDto.SupplierResponseDTO;
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
    
    ApiResponse<List<PetsResponseDTO>> getPetsByCategory(Long categoryId);

    ApiResponse<List<PetsResponseDTO>> getPetsByBreed(String breed);

    ApiResponse<List<PetsResponseDTO>> getPetsByPriceRange(BigDecimal min, BigDecimal max);

    ApiResponse<String> addGroomingServiceToPet(Long petId, Long serviceId);

    ApiResponse<List<GroomingServicesResponseDTO>> getGroomingServicesByPet(Long petId);

    ApiResponse<String> removeGroomingServiceFromPet(Long petId, Long serviceId);

    ApiResponse<String> addVaccinationToPet(Long petId, Long vaccinationId);

    ApiResponse<List<Long>> getVaccinationsByPet(Long petId);

    ApiResponse<String> removeVaccinationFromPet(Long petId, Long vaccinationId);


    ApiResponse<String> addFoodToPet(Long petId, Long foodId);

    ApiResponse<List<Long>> getFoodByPet(Long petId);

    ApiResponse<String> removeFoodFromPet(Long petId, Long foodId);

    ApiResponse<String> addSupplierToPet(Long petId, Long supplierId);

    ApiResponse<List<SupplierResponseDTO>> getSuppliersByPet(Long petId);

}

package com.sprint.pet_shop.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.sprint.pet_shop.dto.requestDto.VaccinationsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.VaccinationsResponseDTO;

// Service interface for Vaccination records.
// We use this to establish the rules for managing the medical treatments available for pets.
public interface VaccinationsInterface {

    // Save vaccinations
    ApiResponse<List<VaccinationsResponseDTO>> saveAllVaccinations(List<VaccinationsRequestDTO> dtos);

    // Get all vaccinations
    ApiResponse<List<VaccinationsResponseDTO>> getAllVaccinations();

    // Get vaccination by ID
    ApiResponse<VaccinationsResponseDTO> getVaccinationsById(long id);

    // Update vaccination by ID
    ApiResponse<VaccinationsResponseDTO> updateVaccinationById(long id, VaccinationsRequestDTO dto);

    // Delete vaccination by ID
    ApiResponse<String> deleteVaccinationsById(long id);
    
    // Get vaccinations by price range
    ApiResponse<List<VaccinationsResponseDTO>> getVaccinationsByPrice(BigDecimal min, BigDecimal max);
}

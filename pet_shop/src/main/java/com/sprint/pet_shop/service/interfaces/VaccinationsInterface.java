package com.sprint.pet_shop.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.sprint.pet_shop.dto.requestDto.VaccinationsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.VaccinationsResponseDTO;

public interface VaccinationsInterface {

    ApiResponse<List<VaccinationsResponseDTO>> saveAllVaccinations(List<VaccinationsRequestDTO> dtos);

    ApiResponse<List<VaccinationsResponseDTO>> getAllVaccinations();

    ApiResponse<VaccinationsResponseDTO> getVaccinationsById(long id);

    ApiResponse<VaccinationsResponseDTO> updateVaccinationById(long id, VaccinationsRequestDTO dto);

    ApiResponse<String> deleteVaccinationsById(long id);
    
    ApiResponse<List<VaccinationsResponseDTO>> getVaccinationsByPrice(BigDecimal min, BigDecimal max);
}
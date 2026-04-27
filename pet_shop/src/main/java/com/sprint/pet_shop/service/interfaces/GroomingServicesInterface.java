package com.sprint.pet_shop.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.sprint.pet_shop.dto.requestDto.GroomingServicesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.GroomingServicesResponseDTO;


// Contract for Grooming Services business logic.
// Ensures we can manage our service catalog, including checking what services 
// fall into a customer's specific price range.

public interface GroomingServicesInterface {

    // Save grooming services
    ApiResponse<List<GroomingServicesResponseDTO>> saveAllGroomingServices(List<GroomingServicesRequestDTO> dtos);

    // Get all grooming services
    ApiResponse<List<GroomingServicesResponseDTO>> getAllGroomingServices();

    // Get grooming service by ID
    ApiResponse<GroomingServicesResponseDTO> getGroomingServiceById(long id);

    // Update grooming service by ID
    ApiResponse<GroomingServicesResponseDTO> updateGroomingService(long id, GroomingServicesRequestDTO dto);

    // Delete grooming service by ID
    ApiResponse<String> deleteGroomingServiceById(long id);

    // Get grooming services by price range
    ApiResponse<List<GroomingServicesResponseDTO>> getServicesByPriceRange(BigDecimal min, BigDecimal max);
}

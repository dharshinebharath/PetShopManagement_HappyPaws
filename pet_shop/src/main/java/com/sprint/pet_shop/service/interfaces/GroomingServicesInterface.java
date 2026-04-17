package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.dto.requestDto.GroomingServicesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.GroomingServicesResponseDTO;
import com.sprint.pet_shop.entity.GroomingServices;

import jakarta.validation.Valid;

public interface GroomingServicesInterface {


    ApiResponse<List<GroomingServicesResponseDTO>> saveAllGroomingServices(List<GroomingServicesRequestDTO> dtos);

    ApiResponse<List<GroomingServicesResponseDTO>> getAllGroomingServices();

    ApiResponse<GroomingServicesResponseDTO> getGroomingServiceById(long id);

    ApiResponse<GroomingServicesResponseDTO> updateGroomingService(long id, GroomingServicesRequestDTO dto);

    ApiResponse<String> deleteGroomingServiceById(long id);
}

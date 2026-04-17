package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.dto.requestDto.AddressesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.AddressesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;

import jakarta.validation.Valid;

public interface AddressesInterface {

    ApiResponse<List<AddressesResponseDTO>> saveaddresses(@Valid List<AddressesRequestDTO> addresses);

    ApiResponse<List<AddressesResponseDTO>> getaddresses();

    ApiResponse<AddressesResponseDTO> getaddressesByID(long id);

    ApiResponse<String> deleteaddress(long id);

    ApiResponse<AddressesResponseDTO> updateaddress(long id, @Valid AddressesRequestDTO updatedaddress);
}
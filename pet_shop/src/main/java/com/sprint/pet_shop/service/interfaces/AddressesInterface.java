package com.sprint.pet_shop.service.interfaces;
import java.util.List;
import java.util.Optional;

import com.sprint.pet_shop.dto.requestDto.AddressesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.AddressesResponseDTO;
import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.GroomingServices;

import jakarta.validation.Valid;

public interface AddressesInterface {
	List<AddressesResponseDTO> saveaddresses(@Valid List<AddressesRequestDTO> addresses);
	List<AddressesResponseDTO> getaddresses();
	AddressesResponseDTO getaddressesByID(long id);
	void deleteaddress(long id);
	AddressesResponseDTO updateaddress(long id,@Valid AddressesRequestDTO updatedaddress);
}

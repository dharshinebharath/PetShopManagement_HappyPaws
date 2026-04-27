package com.sprint.pet_shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint.pet_shop.dto.requestDto.AddressesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.AddressesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.service.interfaces.AddressesInterface;

@Service
public class AddressesService implements AddressesInterface {

    @Autowired
    private AddressesRepository addressesRepository;
    @Override
    public ApiResponse<List<AddressesResponseDTO>> saveaddresses(List<AddressesRequestDTO> addresses) {

        List<Addresses> entities = new ArrayList<>();
        for(AddressesRequestDTO dto : addresses) {	
            Addresses entity = new Addresses();
            entity.setStreet(dto.getStreet());
            entity.setCity(dto.getCity());
            entity.setState(dto.getState());
            entity.setZipCode(dto.getZipCode());
            entities.add(entity);
        }

        List<AddressesResponseDTO> data = addressesRepository.saveAll(entities)
                .stream()
                .map(this::toDto)
                .toList();

        return new ApiResponse<>("Addresses created successfully", true, data);
    }
    @Override
    public ApiResponse<List<AddressesResponseDTO>> getaddresses() {

        List<AddressesResponseDTO> data = addressesRepository.findAllSorted()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new ApiResponse<>("Addresses fetched successfully", true, data);
    }
    @Override
    public ApiResponse<AddressesResponseDTO> getaddressesByID(long id) {

        Addresses address = addressesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));

        return new ApiResponse<>("Address found", true, toDto(address));
    }
    @Override
    public ApiResponse<List<AddressesResponseDTO>> getAddressesByCity(String city) {

        if (city == null || city.trim().isEmpty()) {
            throw new InvalidDataException("City cannot be empty");
        }

        List<Addresses> addresses = addressesRepository.getAddressesByCity(city);

        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("No addresses found for city: " + city);
        }

        List<AddressesResponseDTO> data = addresses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new ApiResponse<>("Addresses fetched successfully", true, data);
    }

    @Override
    public ApiResponse<String> deleteaddress(long id) {

        if (!addressesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Address not found with id: " + id);
        }

        addressesRepository.deleteById(id);

        return new ApiResponse<>("Address deleted successfully", true, "Deleted ID: " + id);
    }
    @Override
    public ApiResponse<AddressesResponseDTO> updateaddress(long id, AddressesRequestDTO updatedaddress) {

        Addresses existing = addressesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));

        existing.setCity(updatedaddress.getCity());
        existing.setState(updatedaddress.getState());
        existing.setStreet(updatedaddress.getStreet());
        existing.setZipCode(updatedaddress.getZipCode());

        Addresses saved = addressesRepository.save(existing);

        return new ApiResponse<>("Address updated successfully", true, toDto(saved));
    }

    private AddressesResponseDTO toDto(Addresses entity) {
        AddressesResponseDTO dto = new AddressesResponseDTO();
        dto.setAddressId(entity.getAddressId());
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZipCode(entity.getZipCode());
        return dto;
    }
}


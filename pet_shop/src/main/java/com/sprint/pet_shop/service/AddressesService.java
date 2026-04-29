package com.sprint.pet_shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint.pet_shop.dto.requestDto.AddressesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.AddressesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.service.interfaces.AddressesInterface;

//  Implementation of the AddressesInterface.
//  This class contains the actual business logic for validating and saving physical addresses 
//  before they reach the database.
@Service
public class AddressesService implements AddressesInterface {

    @Autowired
    private AddressesRepository addressesRepository;

    // Saving addresses to the database
    @Override
    public ApiResponse<List<AddressesResponseDTO>> saveaddresses(List<AddressesRequestDTO> addresses) {

        List<Addresses> entities = addresses.stream().map(dto -> {

            if (dto.getStreet() == null || dto.getStreet().trim().isEmpty()) {
                throw new InvalidDataException("Street cannot be empty");
            }
            if (dto.getCity() == null || dto.getCity().trim().isEmpty()) {
                throw new InvalidDataException("City cannot be empty");
            }
            if (dto.getState() == null || dto.getState().trim().isEmpty()) {
                throw new InvalidDataException("State cannot be empty");
            }
            if (dto.getZipCode() == null || dto.getZipCode().trim().isEmpty()) {
                throw new InvalidDataException("Zip code cannot be empty");
            }
            if (!dto.getZipCode().matches("[0-9]{5}")) {
                throw new InvalidDataException("Zip code must be 5 digits");
            }

            Addresses entity = new Addresses();
            entity.setStreet(dto.getStreet());
            entity.setCity(dto.getCity());
            entity.setState(dto.getState());
            entity.setZipCode(dto.getZipCode());
            return entity;
        }).collect(Collectors.toList());

        List<AddressesResponseDTO> data = addressesRepository.saveAll(entities)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new ApiResponse<>("Addresses created successfully", true, data);
    }

    // Retrieving all addresses from the database
    @Override
    public ApiResponse<List<AddressesResponseDTO>> getaddresses() {

        List<AddressesResponseDTO> data = addressesRepository.findAllSorted()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new ApiResponse<>("Addresses fetched successfully", true, data);
    }

    // Retrieving addresses by ID from the database
    @Override
    public ApiResponse<AddressesResponseDTO> getaddressesByID(long id) {

        Addresses address = addressesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));

        return new ApiResponse<>("Address found", true, toDto(address));
    }

    // Retrieving all addresses by city from the database
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

    // Deleting addresses from the database
    @Override
    public ApiResponse<String> deleteaddress(long id) {

        if (!addressesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Address not found with id: " + id);
        }

        addressesRepository.deleteById(id);

        return new ApiResponse<>("Address deleted successfully", true, "Deleted ID: " + id);
    }

    // Updating addresses in the database
    @Override
    public ApiResponse<AddressesResponseDTO> updateaddress(long id, AddressesRequestDTO updatedaddress) {

        Addresses existing = addressesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));

        if (updatedaddress.getStreet() == null || updatedaddress.getStreet().trim().isEmpty()) {
            throw new InvalidDataException("Street cannot be empty");
        }
        if (updatedaddress.getCity() == null || updatedaddress.getCity().trim().isEmpty()) {
            throw new InvalidDataException("City cannot be empty");
        }
        if (updatedaddress.getState() == null || updatedaddress.getState().trim().isEmpty()) {
            throw new InvalidDataException("State cannot be empty");
        }
        if (updatedaddress.getZipCode() == null || updatedaddress.getZipCode().trim().isEmpty()) {
            throw new InvalidDataException("Zip code cannot be empty");
        }
        if (!updatedaddress.getZipCode().matches("[0-9]{5}")) {
            throw new InvalidDataException("Zip code must be 5 digits");
        }


        existing.setCity(updatedaddress.getCity());
        existing.setState(updatedaddress.getState());
        existing.setStreet(updatedaddress.getStreet());
        existing.setZipCode(updatedaddress.getZipCode());

        Addresses saved = addressesRepository.save(existing);

        return new ApiResponse<>("Address updated successfully", true, toDto(saved));
    }

    // converting addresses to dto
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

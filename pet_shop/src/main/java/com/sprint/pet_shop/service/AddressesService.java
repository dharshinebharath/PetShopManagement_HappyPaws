package com.sprint.pet_shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.AddressesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.AddressesResponseDTO;
import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.service.interfaces.AddressesInterface;

@Service
public class AddressesService implements AddressesInterface {

    @Autowired
    private AddressesRepository addressesRepository;

    //CREATE
    @Override
    public List<AddressesResponseDTO> saveaddresses(List<AddressesRequestDTO> addresses) {

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

            Addresses entity = new Addresses();
            entity.setStreet(dto.getStreet());
            entity.setCity(dto.getCity());
            entity.setState(dto.getState());
            entity.setZipCode(dto.getZipCode());

            return entity;

        }).collect(Collectors.toList());

        return addressesRepository.saveAll(entities)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    //GET ALL
    @Override
    public List<AddressesResponseDTO> getaddresses() {
        return addressesRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    //GET BY ID
    @Override
    public AddressesResponseDTO getaddressesByID(long id) {
        Addresses address = addressesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));

        return toDto(address);
    }

    //DELETE
    @Override
    public void deleteaddress(long id) {
        if (!addressesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Address not found with id: " + id);
        }
        addressesRepository.deleteById(id);
    }

    // UPDATE
    @Override
    public AddressesResponseDTO updateaddress(long id, AddressesRequestDTO updatedaddress) {

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

        existing.setCity(updatedaddress.getCity());
        existing.setState(updatedaddress.getState());
        existing.setStreet(updatedaddress.getStreet());
        existing.setZipCode(updatedaddress.getZipCode());

        Addresses saved = addressesRepository.save(existing);

        return toDto(saved);
    }

    // ENTITY → DTO
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

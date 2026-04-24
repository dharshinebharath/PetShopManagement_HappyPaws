// This service contains the main business flow for pet food service.
package com.sprint.pet_shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.PetFoodRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetFoodResponseDTO;
import com.sprint.pet_shop.entity.PetFood;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.PetFoodRepository;
import com.sprint.pet_shop.service.interfaces.PetFoodInterface;

@Service
public class PetFoodService implements PetFoodInterface {

    @Autowired
    private PetFoodRepository petFoodRepository;

    private PetFoodResponseDTO toDto(PetFood entity) {
        PetFoodResponseDTO dto = new PetFoodResponseDTO();

        dto.setFoodId(entity.getFoodId());
        dto.setName(entity.getName());
        dto.setBrand(entity.getBrand());
        dto.setType(entity.getType());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());

        return dto;
    }

    @Override
    public ApiResponse<List<PetFoodResponseDTO>> saveAllPetFood(List<PetFoodRequestDTO> dtos) {

        List<PetFood> entities = new ArrayList<>();

        for (PetFoodRequestDTO dto : dtos) {

            if (dto.getQuantity() < 0) {
                throw new InvalidDataException("Quantity cannot be negative");
            }

            if (dto.getPrice().doubleValue() < 0) {
                throw new InvalidDataException("Price must be positive");
            }

            if (dto.getName() == null || dto.getName().isBlank()) {
                throw new InvalidDataException("Food name cannot be empty");
            }

            if (petFoodRepository.existsByNameAndBrand(dto.getName(), dto.getBrand())) {
                throw new DuplicateResourceException(
                        "Food already exists: " + dto.getName() + " (" + dto.getBrand() + ")");
            }

            PetFood entity = new PetFood();
            entity.setName(dto.getName());
            entity.setBrand(dto.getBrand());
            entity.setType(dto.getType());
            entity.setQuantity(dto.getQuantity());
            entity.setPrice(dto.getPrice());

            entities.add(entity);
        }

        List<PetFoodResponseDTO> data =
                petFoodRepository.saveAll(entities)
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<PetFoodResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Pet food saved successfully");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }

    @Override
    public ApiResponse<List<PetFoodResponseDTO>> getAllPetFood() {

        List<PetFoodResponseDTO> data =
                petFoodRepository.findAllSorted()
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<PetFoodResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Fetched all pet food");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }

    @Override
    public ApiResponse<PetFoodResponseDTO> getPetFoodById(long id) {

        PetFood entity = petFoodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food Not Found with id: " + id));

        ApiResponse<PetFoodResponseDTO> response = new ApiResponse<>();
        response.setMessage("Pet food fetched successfully");
        response.setSuccess(true);
        response.setData(toDto(entity));

        return response;
    }

    @Override
    public ApiResponse<PetFoodResponseDTO> updatePetFood(long id, PetFoodRequestDTO dto) {

        PetFood existing = petFoodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food Not Found with id: " + id));

        if (dto.getQuantity() < 0) {
            throw new InvalidDataException("Quantity cannot be negative");
        }

        if (dto.getPrice().doubleValue() < 0) {
            throw new InvalidDataException("Price must be positive");
        }

        existing.setName(dto.getName());
        existing.setBrand(dto.getBrand());
        existing.setType(dto.getType());
        existing.setQuantity(dto.getQuantity());
        existing.setPrice(dto.getPrice());

        PetFood updated = petFoodRepository.save(existing);

        ApiResponse<PetFoodResponseDTO> response = new ApiResponse<>();
        response.setMessage("Updated successfully");
        response.setSuccess(true);
        response.setData(toDto(updated));

        return response;
    }

    @Override
    public ApiResponse<String> deletePetFoodById(long id) {

        PetFood existing = petFoodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food Not Found with id: " + id));

        petFoodRepository.delete(existing);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Deleted successfully");
        response.setSuccess(true);
        response.setData("Deleted food id: " + id);

        return response;
    }
}

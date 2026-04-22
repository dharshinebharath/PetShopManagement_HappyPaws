package com.sprint.pet_shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // DTO MAPPER
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

    // VALIDATION
    private void validate(PetFoodRequestDTO dto) {
        if (dto == null) {
            throw new InvalidDataException("Request body cannot be null");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new InvalidDataException("Name cannot be empty");
        }
        if (dto.getBrand() == null || dto.getBrand().isBlank()) {
            throw new InvalidDataException("Brand cannot be empty");
        }
        if (dto.getQuantity() < 0) {
            throw new InvalidDataException("Quantity cannot be negative");
        }
        if (dto.getPrice() == null || dto.getPrice().doubleValue() < 0) {
            throw new InvalidDataException("Price must be positive");
        }
    }

    // SAVE ALL
    @Override
    public ApiResponse<List<PetFoodResponseDTO>> saveAllPetFood(List<PetFoodRequestDTO> dtos) {

        if (dtos == null || dtos.isEmpty()) {
            throw new InvalidDataException("Food list cannot be empty");
        }

        List<PetFood> entities = new ArrayList<>();

        for (PetFoodRequestDTO dto : dtos) {
            validate(dto);

            if (petFoodRepository.existsByNameAndBrand(dto.getName(), dto.getBrand())) {
                throw new DuplicateResourceException("Food already exists: " + dto.getName());
            }

            PetFood entity = new PetFood();
            entity.setName(dto.getName());
            entity.setBrand(dto.getBrand());
            entity.setType(dto.getType());
            entity.setQuantity(dto.getQuantity());
            entity.setPrice(dto.getPrice());

            entities.add(entity);
        }

        List<PetFoodResponseDTO> data = petFoodRepository.saveAll(entities)
                .stream()
                .map(this::toDto)
                .toList();

        return buildResponse("Pet food saved successfully", data);
    }

    // GET ALL
    @Override
    public ApiResponse<List<PetFoodResponseDTO>> getAllPetFood() {
        List<PetFoodResponseDTO> data = petFoodRepository
                .findAll(Sort.by(Sort.Direction.ASC, "foodId"))
                .stream()
                .map(this::toDto)
                .toList();

        return buildResponse("Fetched all pet food", data);
    }

    // GET BY ID
    @Override
    public ApiResponse<PetFoodResponseDTO> getPetFoodById(long id) {
        PetFood entity = petFoodRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Food not found with id: " + id));

        return buildResponse("Pet food fetched successfully", toDto(entity));
    }

    // UPDATE
    @Override
    public ApiResponse<PetFoodResponseDTO> updatePetFood(long id, PetFoodRequestDTO dto) {

        validate(dto);

        PetFood existing = petFoodRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Food not found with id: " + id));

        existing.setName(dto.getName());
        existing.setBrand(dto.getBrand());
        existing.setType(dto.getType());
        existing.setQuantity(dto.getQuantity());
        existing.setPrice(dto.getPrice());

        PetFood updated = petFoodRepository.save(existing);

        return buildResponse("Updated successfully", toDto(updated));
    }

    // DELETE
    @Transactional
    @Override
 
    public ApiResponse<String> deletePetFoodById(long id) {

        PetFood existing = petFoodRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Food not found with id: " + id));

        // 🔥 IMPORTANT: break ManyToMany relationship
        if (existing.getPets() != null) {
            existing.getPets().forEach(pet -> pet.getFoods().remove(existing));
            existing.getPets().clear();
        }

        petFoodRepository.delete(existing);

        return buildResponse("Deleted successfully",
                "PetFood deleted successfully with id: " + id);
    }

    // COMMON RESPONSE
    private <T> ApiResponse<T> buildResponse(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMessage(message);
        response.setSuccess(true);
        response.setData(data);
        return response;
    }
}
package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.pet_shop.dto.requestDto.PetFoodRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetFoodResponseDTO;
import com.sprint.pet_shop.service.interfaces.PetFoodInterface;

import jakarta.validation.Valid;

// REST Controller for pet food inventory.
// Exposes endpoints to track food stock levels, update product details, 
// and remove items that are no longer sold.
@RestController
// Base URL for all endpoints in this controller.
@RequestMapping("/api/v1/food")
public class PetFoodController {

    @Autowired
    private PetFoodInterface petFoodService;


    // POST /api/v1/food - Add a list of pet food products.
    @PostMapping
    public ResponseEntity<ApiResponse<List<PetFoodResponseDTO>>> saveAll(
            @Valid @RequestBody List<PetFoodRequestDTO> dtos) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(petFoodService.saveAllPetFood(dtos));
    }


    // GET /api/v1/food - Get all pet food products.
    @GetMapping
    public ResponseEntity<ApiResponse<List<PetFoodResponseDTO>>> getAll() {
        return ResponseEntity.ok(petFoodService.getAllPetFood());
    }

    
    // GET /api/v1/food/{id} - Get pet food by ID.
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PetFoodResponseDTO>> getById(@PathVariable long id) {
        return ResponseEntity.ok(petFoodService.getPetFoodById(id));
    }


    // PUT /api/v1/food/{id} - Update pet food by ID.
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PetFoodResponseDTO>> update(
            @PathVariable long id,
            @Valid @RequestBody PetFoodRequestDTO dto) {

        return ResponseEntity.ok(petFoodService.updatePetFood(id, dto));
    }


    // DELETE /api/v1/food/{id} - Delete pet food by ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id) {
        return ResponseEntity.ok(petFoodService.deletePetFoodById(id));
    }
    
}

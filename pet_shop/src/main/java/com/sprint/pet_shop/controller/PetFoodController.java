package com.sprint.pet_shop.controller;

import java.math.BigDecimal;
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
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/food")
public class PetFoodController {

    @Autowired
    private PetFoodInterface petFoodService;

    // 🔹 CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<List<PetFoodResponseDTO>>> saveAll(
            @Valid @RequestBody List<PetFoodRequestDTO> dtos) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(petFoodService.saveAllPetFood(dtos));
    }

    // 🔹 GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<PetFoodResponseDTO>>> getAll() {
        return ResponseEntity.ok(petFoodService.getAllPetFood());
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PetFoodResponseDTO>> getById(@PathVariable long id) {
        return ResponseEntity.ok(petFoodService.getPetFoodById(id));
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PetFoodResponseDTO>> update(
            @PathVariable long id,
            @Valid @RequestBody PetFoodRequestDTO dto) {

        return ResponseEntity.ok(petFoodService.updatePetFood(id, dto));
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id) {
        return ResponseEntity.ok(petFoodService.deletePetFoodById(id));
    }
    
}
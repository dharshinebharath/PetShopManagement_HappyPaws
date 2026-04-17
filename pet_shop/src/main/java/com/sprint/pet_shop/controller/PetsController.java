package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.pet_shop.dto.requestDto.PetsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetsResponseDTO;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.service.PetsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pets")
public class PetsController {

	  @Autowired
	    private PetsService petsService;

	    // ✅ CREATE (SAVE ALL)
	    @PostMapping
	    public ResponseEntity<ApiResponse<List<PetsResponseDTO>>> addAllPets(
	            @Valid @RequestBody List<PetsRequestDTO> dtos) {

	        ApiResponse<List<PetsResponseDTO>> response =
	                petsService.addAllPets(dtos);

	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }

	    // ✅ GET ALL
	    @GetMapping
	    public ResponseEntity<ApiResponse<List<PetsResponseDTO>>> getAllPets() {

	        ApiResponse<List<PetsResponseDTO>> response =
	                petsService.getAllPets();

	        return ResponseEntity.ok(response);
	    }

	    // ✅ GET BY ID
	    @GetMapping("/{id}")
	    public ResponseEntity<ApiResponse<PetsResponseDTO>> getPetById(
	            @PathVariable long id) {

	        ApiResponse<PetsResponseDTO> response =
	                petsService.getPetById(id);

	        return ResponseEntity.ok(response);
	    }

	    // ✅ UPDATE
	    @PutMapping("/{id}")
	    public ResponseEntity<ApiResponse<PetsResponseDTO>> updatePet(
	            @PathVariable long id,
	            @Valid @RequestBody PetsRequestDTO dto) {

	        ApiResponse<PetsResponseDTO> response =
	                petsService.updatePets(id, dto);

	        return ResponseEntity.ok(response);
	    }

	    // ✅ DELETE
	    @DeleteMapping("/{id}")
	    public ResponseEntity<ApiResponse<String>> deletePet(
	            @PathVariable long id) {

	        ApiResponse<String> response =
	                petsService.deletePets(id);

	        return ResponseEntity.ok(response);
	    }
	}
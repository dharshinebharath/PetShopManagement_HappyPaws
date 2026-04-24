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

import com.sprint.pet_shop.dto.requestDto.PetCategoriesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetCategoriesResponseDTO;
import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.service.PetCategoriesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/categories")
public class PetCategoriesController {

	@Autowired
	private PetCategoriesService service;
	
    @PostMapping
    public ResponseEntity<ApiResponse<List<PetCategoriesResponseDTO>>> addAll(
            @Valid @RequestBody List<PetCategoriesRequestDTO> dtos) {

        ApiResponse<List<PetCategoriesResponseDTO>> response =
                service.addAll(dtos);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PetCategoriesResponseDTO>>> getAll() {

        ApiResponse<List<PetCategoriesResponseDTO>> response =
                service.getAllCategories();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PetCategoriesResponseDTO>> getById(
            @PathVariable long id) {

        ApiResponse<PetCategoriesResponseDTO> response =
                service.getCategoryById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PetCategoriesResponseDTO>> update(
            @PathVariable long id,
            @Valid @RequestBody PetCategoriesRequestDTO dto) {

        ApiResponse<PetCategoriesResponseDTO> response =
                service.updateCategory(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable long id) {

        ApiResponse<String> response =
                service.deleteCategory(id);

        return ResponseEntity.ok(response);
    }
}

package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.pet_shop.dto.requestDto.SupplierRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.SupplierResponseDTO;
import com.sprint.pet_shop.service.SupplierService;

import jakarta.validation.Valid;

/**
 * REST Controller for vendor and supplier management.
 * Facilitates the addition of new suppliers and linking them to the specific 
 * animals they provide to the shop.
 */
@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    @PostMapping
    public ResponseEntity<ApiResponse<List<SupplierResponseDTO>>> saveAll(
            @Valid @RequestBody List<SupplierRequestDTO> suppliers) {

        ApiResponse<List<SupplierResponseDTO>> response =
                supplierService.saveAll(suppliers);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<SupplierResponseDTO>>> getAll() {

        ApiResponse<List<SupplierResponseDTO>> response =
                supplierService.getAll();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponseDTO>> getById(
            @PathVariable long id) {

        ApiResponse<SupplierResponseDTO> response =
                supplierService.getSupplierById(id);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteSupplier(
            @PathVariable long id) {

        ApiResponse<String> response =
                supplierService.deleteSupplier(id);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponseDTO>> updateSupplier(
            @PathVariable long id,
            @Valid @RequestBody SupplierRequestDTO dto) {

        ApiResponse<SupplierResponseDTO> response =
                supplierService.updateSupplier(id, dto);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/pet")
    public ApiResponse<List<SupplierResponseDTO>> getByPet(
            @RequestParam String petName) {
        return supplierService.getSuppliersByPet(petName);
    }
}


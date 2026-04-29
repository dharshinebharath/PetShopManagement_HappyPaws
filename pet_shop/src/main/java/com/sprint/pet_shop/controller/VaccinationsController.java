
package com.sprint.pet_shop.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.pet_shop.dto.requestDto.VaccinationsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.VaccinationsResponseDTO;
import com.sprint.pet_shop.service.interfaces.VaccinationsInterface;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")

//  REST Controller for pet vaccinations.
//  Manages the records of vaccinations available in the shop, including their pricing and availability status.

@RestController

// Base URL for the API
@RequestMapping("/api/v1/vaccinations")
public class VaccinationsController {

    // Inject the service
    @Autowired
    private VaccinationsInterface vaccinationsService;

    // HTTP POST request to add new vaccinations
    @PostMapping
    public ResponseEntity<ApiResponse<List<VaccinationsResponseDTO>>> saveAll(
             @RequestBody List<@Valid VaccinationsRequestDTO> dtos) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vaccinationsService.saveAllVaccinations(dtos));
    }

    // HTTP GET request to fetch all vaccinations
    @GetMapping
    public ResponseEntity<ApiResponse<List<VaccinationsResponseDTO>>> getAll() {
        return ResponseEntity.ok(vaccinationsService.getAllVaccinations());
    }

    // HTTP GET request to fetch a vaccination by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VaccinationsResponseDTO>> getById(
            @PathVariable long id) {

        return ResponseEntity.ok(vaccinationsService.getVaccinationsById(id));
    }

    // HTTP PUT request to update a vaccination by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VaccinationsResponseDTO>> update(
            @PathVariable long id,
             @RequestBody @Valid VaccinationsRequestDTO dto) {

        return ResponseEntity.ok(
                vaccinationsService.updateVaccinationById(id, dto));
    }

    // HTTP DELETE request to delete a vaccination by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id) {
        return ResponseEntity.ok(
                vaccinationsService.deleteVaccinationsById(id));
    }

    // HTTP GET request to fetch vaccinations by price range
    @GetMapping("/price")
    public ApiResponse<List<VaccinationsResponseDTO>> getVaccinationsByPrice(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {

        return vaccinationsService.getVaccinationsByPrice(min, max);
    }
}

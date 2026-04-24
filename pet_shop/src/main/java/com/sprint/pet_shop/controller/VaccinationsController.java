// This controller exposes the HTTP endpoints for vaccinations controller.
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

@RestController
@RequestMapping("/api/v1/vaccinations")
public class VaccinationsController {

    @Autowired
    private VaccinationsInterface vaccinationsService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<VaccinationsResponseDTO>>> saveAll(
            @Valid @RequestBody List<VaccinationsRequestDTO> dtos) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vaccinationsService.saveAllVaccinations(dtos));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VaccinationsResponseDTO>>> getAll() {
        return ResponseEntity.ok(vaccinationsService.getAllVaccinations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VaccinationsResponseDTO>> getById(
            @PathVariable long id) {

        return ResponseEntity.ok(vaccinationsService.getVaccinationsById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VaccinationsResponseDTO>> update(
            @PathVariable long id,
            @Valid @RequestBody VaccinationsRequestDTO dto) {

        return ResponseEntity.ok(
                vaccinationsService.updateVaccinationById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id) {
        return ResponseEntity.ok(
                vaccinationsService.deleteVaccinationsById(id));
    }
    @GetMapping("/price")
    public ApiResponse<List<VaccinationsResponseDTO>> getVaccinationsByPrice(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {

        return vaccinationsService.getVaccinationsByPrice(min, max);
    }
}

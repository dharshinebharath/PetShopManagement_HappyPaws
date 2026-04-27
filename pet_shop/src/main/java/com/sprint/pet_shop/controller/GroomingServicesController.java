package com.sprint.pet_shop.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.pet_shop.dto.requestDto.GroomingServicesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.GroomingServicesResponseDTO;
import com.sprint.pet_shop.service.GroomingServicesService;

import jakarta.validation.Valid;

// Allow the frontend to connect
@CrossOrigin(origins = "http://localhost:4200")

//  REST Controller for grooming services.
//  Allows the frontend to fetch available grooming services, update pricing and manage the catalog of services offered.

@RestController

// Base URL for the API
@RequestMapping("/api/v1/grooming-services")
public class GroomingServicesController {

	// Inject the service
	@Autowired
	private GroomingServicesService groomingServicesService;

	// HTTP Post request to add new grooming services
	@PostMapping
	public ResponseEntity<ApiResponse<List<GroomingServicesResponseDTO>>> saveAll(
			 @RequestBody List<@Valid GroomingServicesRequestDTO> dtos) {

		ApiResponse<List<GroomingServicesResponseDTO>> response = groomingServicesService.saveAllGroomingServices(dtos);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// HTTP Get request to fetch all grooming services
	@GetMapping
	public ResponseEntity<ApiResponse<List<GroomingServicesResponseDTO>>> getAll() {

		ApiResponse<List<GroomingServicesResponseDTO>> response = groomingServicesService.getAllGroomingServices();

		return ResponseEntity.ok(response);
	}

	// HTTP Get request to fetch a grooming service by ID
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<GroomingServicesResponseDTO>> getById(
			@PathVariable long id) {

		ApiResponse<GroomingServicesResponseDTO> response = groomingServicesService.getGroomingServiceById(id);

		return ResponseEntity.ok(response);
	}

	// HTTP Delete request to delete a grooming service by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteGroomingServiceById(
			@PathVariable("id") long id) {

		ApiResponse<String> response = groomingServicesService.deleteGroomingServiceById(id);

		return ResponseEntity.ok(response);
	}

	// HTTP Put request to update a grooming service by ID
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<GroomingServicesResponseDTO>> update(
			@PathVariable long id,
			 @RequestBody @Valid GroomingServicesRequestDTO dto) {

		ApiResponse<GroomingServicesResponseDTO> response = groomingServicesService.updateGroomingService(id, dto);

		return ResponseEntity.ok(response);
	}

	// HTTP Get request to fetch grooming services by price range
	@GetMapping("/price-range")
	public ApiResponse<List<GroomingServicesResponseDTO>> getByPrice(
			@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {
		return groomingServicesService.getServicesByPriceRange(min, max);
	}
}

package com.sprint.pet_shop.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sprint.pet_shop.dto.requestDto.AddressesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.AddressesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.service.AddressesService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")

//  REST Controller for managing addresses.
//  Exposes endpoints for creating, retrieving, updating, and deleting physical addresses.
@RestController

//  Base URL for all endpoints in this controller.
@RequestMapping("/api/v1/addresses")
public class AddressesController {

    @Autowired
    private AddressesService addressesService;

    //  POST /api/v1/addresses - Save a list of addresses.
    @PostMapping
    public ResponseEntity<ApiResponse<List<AddressesResponseDTO>>> saveaddresses(
            @Valid @RequestBody List<AddressesRequestDTO> addresses) {

        ApiResponse<List<AddressesResponseDTO>> response =
                addressesService.saveaddresses(addresses);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //  GET /api/v1/addresses - Get all addresses.
    @GetMapping
    public ResponseEntity<ApiResponse<List<AddressesResponseDTO>>> getaddresses() {

        ApiResponse<List<AddressesResponseDTO>> response =
                addressesService.getaddresses();

        return ResponseEntity.ok(response);
    }

    //  GET /api/v1/addresses/{id} - Get an address by ID.
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressesResponseDTO>> getaddressesbyId(
            @PathVariable long id) {

        ApiResponse<AddressesResponseDTO> response =
                addressesService.getaddressesByID(id);

        return ResponseEntity.ok(response);
    }
    
    //  PUT /api/v1/addresses/{id} - Update an address.
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressesResponseDTO>> updateaddresses(
            @PathVariable long id,
            @Valid @RequestBody AddressesRequestDTO address) {

        ApiResponse<AddressesResponseDTO> response =
                addressesService.updateaddress(id, address);

        return ResponseEntity.ok(response);
    }

    //  DELETE /api/v1/addresses/{id} - Delete an address.
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id) {

        ApiResponse<String> response =
                addressesService.deleteaddress(id);

        return ResponseEntity.ok(response);
    }

    //  GET /api/v1/addresses/city - Get addresses by city.
    @GetMapping("/city")
    public ApiResponse<List<AddressesResponseDTO>> getByCity(@RequestParam String city) {
        return addressesService.getAddressesByCity(city);
    }
}


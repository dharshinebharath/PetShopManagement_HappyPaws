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

@RequestMapping("/api/v1/addresses")
public class AddressesController {

    @Autowired
    private AddressesService addressesService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<AddressesResponseDTO>>> saveaddresses(
            @Valid @RequestBody List<AddressesRequestDTO> addresses) {

        ApiResponse<List<AddressesResponseDTO>> response =
                addressesService.saveaddresses(addresses);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AddressesResponseDTO>>> getaddresses() {

        ApiResponse<List<AddressesResponseDTO>> response =
                addressesService.getaddresses();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressesResponseDTO>> getaddressesbyId(
            @PathVariable long id) {

        ApiResponse<AddressesResponseDTO> response =
                addressesService.getaddressesByID(id);

        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressesResponseDTO>> updateaddresses(
            @PathVariable long id,
            @Valid @RequestBody AddressesRequestDTO address) {

        ApiResponse<AddressesResponseDTO> response =
                addressesService.updateaddress(id, address);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id) {

        ApiResponse<String> response =
                addressesService.deleteaddress(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/city")
    public ApiResponse<List<AddressesResponseDTO>> getByCity(@RequestParam String city) {
        return addressesService.getAddressesByCity(city);
    }
}


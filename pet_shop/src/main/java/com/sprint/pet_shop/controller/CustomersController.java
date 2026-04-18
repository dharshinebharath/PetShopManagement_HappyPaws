package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.pet_shop.dto.requestDto.CustomerRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.CustomerResponseDTO;
import com.sprint.pet_shop.service.CustomersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {
    @Autowired
    private CustomersService customersService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> savecustomers(
            @Valid @RequestBody List<CustomerRequestDTO> customers) {
        ApiResponse<List<CustomerResponseDTO>> response =
                customersService.savecustomers(customers);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> getcustomers() {

        ApiResponse<List<CustomerResponseDTO>> response =
                customersService.getcustomers();

        return ResponseEntity.ok(response);
    }
    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getcustomerbyId(
            @PathVariable Long id) {

        ApiResponse<CustomerResponseDTO> response =
                customersService.getcustomerByID(id);

        return ResponseEntity.ok(response);
    }
    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> updatecustomers(
            @PathVariable long id,
            @Valid @RequestBody CustomerRequestDTO customer) {

        ApiResponse<CustomerResponseDTO> response =
                customersService.updatecustomer(id, customer);

        return ResponseEntity.ok(response);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletecustomer(
            @PathVariable long id) {

        ApiResponse<String> response =
                customersService.deletecustomer(id);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/no-transactions")
    public ApiResponse<List<CustomerResponseDTO>> getCustomersWithNoTransactions() {
        return customersService.getCustomersWithNoTransactions();
    }
}
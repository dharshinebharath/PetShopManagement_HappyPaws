package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.pet_shop.dto.requestDto.CustomerRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.CustomerResponseDTO;
import com.sprint.pet_shop.dto.responseDto.TransactionsResponseDTO;
import com.sprint.pet_shop.service.CustomersService;
import com.sprint.pet_shop.service.TransactionsService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
//  REST Controller for managing customers.
//  Handles HTTP requests for registering new customers, updating their details, 
//  and viewing their transaction history.  
@RestController
// Base URL for all endpoints in this controller.
@RequestMapping("/api/v1/customers")
public class CustomersController {
    @Autowired
    private CustomersService customersService;

    @Autowired
    private TransactionsService transactionsService;
    
    // POST /api/v1/customers - Save a list of customers.
    @PostMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> savecustomers(
            @Valid @RequestBody List<CustomerRequestDTO> customers) {
        ApiResponse<List<CustomerResponseDTO>> response =
                customersService.savecustomers(customers);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/v1/customers - Get all customers.
    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> getcustomers() {

        ApiResponse<List<CustomerResponseDTO>> response =
                customersService.getcustomers();

        return ResponseEntity.ok(response);
    }

    // GET /api/v1/customers/{id} - Get customer by ID.
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getcustomerbyId(
            @PathVariable Long id) {

        ApiResponse<CustomerResponseDTO> response =
                customersService.getcustomerByID(id);

        return ResponseEntity.ok(response);
    }

    // PUT /api/v1/customers/{id} - Update customer by ID.
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> updatecustomers(
            @PathVariable long id,
            @Valid @RequestBody CustomerRequestDTO customer) {

        ApiResponse<CustomerResponseDTO> response =
                customersService.updatecustomer(id, customer);

        return ResponseEntity.ok(response);
    }

    // DELETE /api/v1/customers/{id} - Delete customer by ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletecustomer(
            @PathVariable long id) {

        ApiResponse<String> response =
                customersService.deletecustomer(id);

        return ResponseEntity.ok(response);
    }
    
    // GET /api/v1/customers/no-transactions - Get all customers with no transactions.
    @GetMapping("/no-transactions")
    public ApiResponse<List<CustomerResponseDTO>> getCustomersWithNoTransactions() {
        return customersService.getCustomersWithNoTransactions();
    }
    
    // GET /api/v1/customers/{customerId}/transactions - Get all transactions for a customer.
    @GetMapping("/{customerId}/transactions")
    public ApiResponse<List<TransactionsResponseDTO>> getByCustomer(
            @PathVariable Long customerId) {

        return transactionsService.getByCustomer(customerId);
    }
}


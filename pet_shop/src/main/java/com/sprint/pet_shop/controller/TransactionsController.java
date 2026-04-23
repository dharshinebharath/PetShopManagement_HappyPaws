package com.sprint.pet_shop.controller;

import java.sql.Date;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.pet_shop.dto.requestDto.TransactionsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.TransactionsResponseDTO;
import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.service.TransactionsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")

public class TransactionsController {
	@Autowired
	private TransactionsService transactionsService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<TransactionsResponseDTO>> save(
            @Valid @RequestBody TransactionsRequestDTO dto) {

        ApiResponse<TransactionsResponseDTO> response =
                transactionsService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	//getall
	 @GetMapping
	 public ResponseEntity<ApiResponse<List<TransactionsResponseDTO>>> getAll() {

	        ApiResponse<List<TransactionsResponseDTO>> response =
	                transactionsService.getAll();

	        return ResponseEntity.ok(response);
	    }
	 //get by id
	 @GetMapping("/{id}")
	 public ResponseEntity<ApiResponse<TransactionsResponseDTO>> getById(
	            @PathVariable Long id) {

	        ApiResponse<TransactionsResponseDTO> response =
	                transactionsService.getById(id);

	        return ResponseEntity.ok(response);
	    }
	 @PutMapping("/{id}")
	 public ResponseEntity<ApiResponse<TransactionsResponseDTO>> update(
	            @PathVariable Long id,
	            @Valid @RequestBody TransactionsRequestDTO dto) {

	        ApiResponse<TransactionsResponseDTO> response =
	                transactionsService.update(id, dto);

	        return ResponseEntity.ok(response);
	    }
	    // DELETE
	    @DeleteMapping("/{id}")
	    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {

	        ApiResponse<String> response =
	                transactionsService.delete(id);

	        return ResponseEntity.ok(response);
	    }
	    @GetMapping("/date-range")
	    public ApiResponse<List<TransactionsResponseDTO>> getByDateRange(
	            @RequestParam("startDate") LocalDate startDate,
	            @RequestParam("endDate") LocalDate endDate) {

	        return transactionsService.getByDateRange(startDate, endDate);
	    }
	    
	   

	    // ✅ STATUS FILTER
	    @GetMapping("/status/{status}")
	    public ApiResponse<List<TransactionsResponseDTO>> getByStatus(
	            @PathVariable String status) {

	        return transactionsService.getByStatus(status);
	    }
	 

}

package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.pet_shop.dto.requestDto.EmployeesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.EmployeesResponseDTO;
import com.sprint.pet_shop.service.EmployeesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {

	@Autowired
    private EmployeesService employeesService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<List<EmployeesResponseDTO>>> saveAll(
            @Valid @RequestBody List<EmployeesRequestDTO> employees) {

        return ResponseEntity.ok(employeesService.saveAll(employees));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeesResponseDTO>>> getAll() {

        return ResponseEntity.ok(employeesService.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeesResponseDTO>> getById(@PathVariable long id) {

        return ResponseEntity.ok(employeesService.getEmployeesById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeesResponseDTO>> update(
            @PathVariable long id,
            @RequestBody EmployeesRequestDTO dto) {

        return ResponseEntity.ok(employeesService.updateEmployee(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id) {

        return ResponseEntity.ok(employeesService.deleteEmployee(id));
    }
}
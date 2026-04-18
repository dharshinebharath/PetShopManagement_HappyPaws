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

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    // POST ALL
    @PostMapping
    public ResponseEntity<ApiResponse<List<EmployeesResponseDTO>>> saveAll(
            @Valid @RequestBody List<EmployeesRequestDTO> employees) {

        ApiResponse<List<EmployeesResponseDTO>> response =
                employeesService.saveAll(employees);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeesResponseDTO>>> getAllEmployees() {

        ApiResponse<List<EmployeesResponseDTO>> response =
                employeesService.getAll();

        return ResponseEntity.ok(response);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeesResponseDTO>> getEmployeesById(
            @PathVariable long id) {

        ApiResponse<EmployeesResponseDTO> response =
                employeesService.getEmployeesById(id);

        return ResponseEntity.ok(response);
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable long id) {

        employeesService.deleteEmployee(id);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Deleted successfully");
        response.setSuccess(true);
        response.setData("Employee deleted successfully with id: " + id);

        return ResponseEntity.ok(response);
    }

    // UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeesResponseDTO>> updateEmployee(
            @PathVariable long id,
            @Valid @RequestBody EmployeesRequestDTO employees) {

        ApiResponse<EmployeesResponseDTO> response =
                employeesService.updateEmployee(id, employees);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/position")
    public ApiResponse<List<EmployeesResponseDTO>> getByPosition(@RequestParam String position) {
        return employeesService.getEmployeesByPosition(position);
    }
}
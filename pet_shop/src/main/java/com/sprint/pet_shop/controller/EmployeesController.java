package com.sprint.pet_shop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint.pet_shop.dto.requestDto.EmployeesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.EmployeesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.PetsResponseDTO;
import com.sprint.pet_shop.service.EmployeesService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
//  REST Controller for employee management.
//  Provides endpoints for HR-related tasks like onboarding new staff, assigning pets, 
//  and querying employees by position or hire date.
@RestController
//  Base URL for all endpoints in this controller.
@RequestMapping("/api/v1/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    // POST /api/v1/employees - Save a list of employees.
    @PostMapping
    public ResponseEntity<ApiResponse<List<EmployeesResponseDTO>>> saveAll(
            @Valid @RequestBody List<EmployeesRequestDTO> employees) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeesService.saveAll(employees));
    }

    // GET /api/v1/employees - Get all employees.
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeesResponseDTO>>> getAllEmployees() {
        return ResponseEntity.ok(employeesService.getAll());
    }

    // GET /api/v1/employees/{id} - Get employee by ID.
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeesResponseDTO>> getEmployeesById(
            @PathVariable long id) {
        return ResponseEntity.ok(employeesService.getEmployeesById(id));
    }

    // DELETE /api/v1/employees/{id} - Delete employee by ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable long id) {
        return ResponseEntity.ok(employeesService.deleteEmployee(id));
    }

    // PUT /api/v1/employees/{id} - Update employee by ID.
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeesResponseDTO>> updateEmployee(
            @PathVariable long id,
            @Valid @RequestBody EmployeesRequestDTO employees) {

        return ResponseEntity.ok(employeesService.updateEmployee(id, employees));
    }

    // POST /api/v1/employees/{employeeId}/pets/{petId} - Assign a pet to an employee.
    @PostMapping("{employeeId}/pets/{petId}")
    public ApiResponse<String> assign(
            @PathVariable Long employeeId,
            @PathVariable Long petId) {

        return employeesService.assignPetToEmployee(employeeId, petId);
    }

    // GET /api/v1/employees/{employeeId}/pets - Get all pets assigned to an employee.
    @GetMapping("/{employeeId}/pets")
    public ApiResponse<List<PetsResponseDTO>> getPets(@PathVariable Long employeeId) {
        return employeesService.getPetsByEmployee(employeeId);
    }

    // DELETE /api/v1/employees/{employeeId}/pets/{petId} - Remove a pet from an employee.
    @DeleteMapping("/{employeeId}/pets/{petId}")
    public ApiResponse<String> remove(
            @PathVariable Long employeeId,
            @PathVariable Long petId) {

        return employeesService.removePetFromEmployee(employeeId, petId);
    }

    // GET /api/v1/employees/role/{position} - Get employees by position.
    @GetMapping("/role/{position}")
    public ApiResponse<List<EmployeesResponseDTO>> getByPosition(
            @PathVariable String position) {

        return employeesService.getEmployeesByPosition(position);
    }

    // GET /api/v1/employees/hired-after/{date} - Get employees hired after a specific date.
    @GetMapping("/hired-after/{date}")
    public ApiResponse<List<EmployeesResponseDTO>> getByHireDate(
        @PathVariable
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date) {

        return employeesService.getEmployeesHiredAfter(date);
    }
}

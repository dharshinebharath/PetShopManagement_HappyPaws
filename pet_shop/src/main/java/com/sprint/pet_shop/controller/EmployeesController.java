package com.sprint.pet_shop.controller;

import java.time.LocalDate;
import java.util.Date;
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
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<List<EmployeesResponseDTO>>> saveAll(
            @Valid @RequestBody List<EmployeesRequestDTO> employees) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeesService.saveAll(employees));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeesResponseDTO>>> getAllEmployees() {
        return ResponseEntity.ok(employeesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeesResponseDTO>> getEmployeesById(
            @PathVariable long id) {
        return ResponseEntity.ok(employeesService.getEmployeesById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable long id) {
        return ResponseEntity.ok(employeesService.deleteEmployee(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeesResponseDTO>> updateEmployee(
            @PathVariable long id,
            @Valid @RequestBody EmployeesRequestDTO employees) {

        return ResponseEntity.ok(employeesService.updateEmployee(id, employees));
    }

    @PostMapping("{employeeId}/pets/{petId}")
    public ApiResponse<String> assign(
            @PathVariable Long employeeId,
            @PathVariable Long petId) {

        return employeesService.assignPetToEmployee(employeeId, petId);
    }

    @GetMapping("/{employeeId}/pets")
    public ApiResponse<List<PetsResponseDTO>> getPets(@PathVariable Long employeeId) {
        return employeesService.getPetsByEmployee(employeeId);
    }

    @DeleteMapping("/{employeeId}/pets/{petId}")
    public ApiResponse<String> remove(
            @PathVariable Long employeeId,
            @PathVariable Long petId) {

        return employeesService.removePetFromEmployee(employeeId, petId);
    }
    
    

    @GetMapping("/role/{position}")
    public ApiResponse<List<EmployeesResponseDTO>> getByPosition(
            @PathVariable String position) {

        return employeesService.getEmployeesByPosition(position);
    }

    @GetMapping("/hired-after/{date}")
    public ApiResponse<List<EmployeesResponseDTO>> getByHireDate(
        @PathVariable
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date) {

        return employeesService.getEmployeesHiredAfter(date);
    }
}

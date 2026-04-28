package com.sprint.pet_shop.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.sprint.pet_shop.dto.requestDto.EmployeesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.EmployeesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.PetsResponseDTO;

/**
 * Service interface for Employee operations.
 * Outlines how we handle staff data, assign them to care for specific pets, 
 * and fetch their details based on their role or hire date.
 */
public interface EmployeesInterface {

    ApiResponse<List<EmployeesResponseDTO>> saveAll(List<EmployeesRequestDTO> employees);

    ApiResponse<List<EmployeesResponseDTO>> getAll();

    ApiResponse<EmployeesResponseDTO> getEmployeesById(long employeesId);

    ApiResponse<String> deleteEmployee(long employeeId);

    ApiResponse<EmployeesResponseDTO> updateEmployee(Long id, EmployeesRequestDTO employee);
    
    ApiResponse<List<EmployeesResponseDTO>> getEmployeesByPosition(String position);

	ApiResponse<String> assignPetToEmployee(Long employeeId, Long petId);

	ApiResponse<List<PetsResponseDTO>> getPetsByEmployee(Long employeeId);

	ApiResponse<String> removePetFromEmployee(Long employeeId, Long petId);

	ApiResponse<List<EmployeesResponseDTO>> getEmployeesByPet(Long petId);

    ApiResponse<List<EmployeesResponseDTO>> getEmployeesHiredAfter(LocalDate date);

    

}

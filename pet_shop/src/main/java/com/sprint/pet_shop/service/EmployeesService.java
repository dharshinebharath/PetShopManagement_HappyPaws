
package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.EmployeesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.EmployeesResponseDTO;
import com.sprint.pet_shop.entity.Employees;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.repository.EmployeesRepository;
import com.sprint.pet_shop.service.interfaces.EmployeesInterface;

@Service
public class EmployeesService implements EmployeesInterface {

    @Autowired
    private EmployeesRepository employeesRepository;

    // ENTITY → DTO
    private EmployeesResponseDTO toDto(Employees entity) {

        EmployeesResponseDTO dto = new EmployeesResponseDTO();

        dto.setEmployeeId(entity.getEmployeeId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPosition(entity.getPosition());
        dto.setHireDate(entity.getHireDate());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());

        return dto;
    }

    // SAVE ALL
    @Override
    public ApiResponse<List<EmployeesResponseDTO>> saveAll(List<EmployeesRequestDTO> employees) {

        if (employees == null || employees.isEmpty()) {
            throw new InvalidDataException("Employee list cannot be empty");
        }

        List<Employees> entities = employees.stream().map(dto -> {

            Employees emp = new Employees();

            if (dto.getFirstName() != null && dto.getFirstName().isEmpty()) {
                throw new InvalidDataException("First name cannot be empty");
            }

            if (dto.getEmail() != null &&
                    employeesRepository.existsByEmail(dto.getEmail())) {
                throw new DuplicateResourceException("Employee email already exists");
            }

            emp.setFirstName(dto.getFirstName());
            emp.setLastName(dto.getLastName());
            emp.setPosition(dto.getPosition());
            emp.setHireDate(dto.getHireDate());
            emp.setPhoneNumber(dto.getPhoneNumber());
            emp.setEmail(dto.getEmail());

            return emp;

        }).toList();

        List<Employees> saved = employeesRepository.saveAll(entities);

        List<EmployeesResponseDTO> responseList =
                saved.stream().map(this::toDto).toList();

        ApiResponse<List<EmployeesResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Employees saved successfully");
        response.setSuccess(true);
        response.setData(responseList);

        return response;
    }

    // GET ALL
    @Override
    public ApiResponse<List<EmployeesResponseDTO>> getAll() {

        List<EmployeesResponseDTO> data =
                employeesRepository.findAll()
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<EmployeesResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Fetched all employees");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }

    // GET BY ID
    @Override
    public ApiResponse<EmployeesResponseDTO> getEmployeesById(long employeesId) {

        Employees entity = employeesRepository.findById(employeesId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + employeesId));

        ApiResponse<EmployeesResponseDTO> response = new ApiResponse<>();
        response.setMessage("Employee fetched successfully");
        response.setSuccess(true);
        response.setData(toDto(entity));

        return response;
    }

    // DELETE (NOW RETURN API RESPONSE LIKE GROOMING)
    @Override
    public ApiResponse<String> deleteEmployee(long employeeId) {

        Employees existing = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + employeeId));

        employeesRepository.delete(existing);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Deleted successfully");
        response.setSuccess(true);
        response.setData("Employee deleted successfully with id: " + employeeId);

        return response;
    }

    // UPDATE (FIXED → DTO STYLE LIKE GROOMING)
    @Override
    public ApiResponse<EmployeesResponseDTO> updateEmployee(Long id, EmployeesRequestDTO dto) {

        Employees existing = employeesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));

        if (dto.getFirstName() != null && dto.getFirstName().isEmpty()) {
            throw new InvalidDataException("First name cannot be empty");
        }

        if (dto.getEmail() != null &&
                employeesRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Employee email already exists");
        }

        if (dto.getFirstName() != null) {
            existing.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            existing.setLastName(dto.getLastName());
        }
        if (dto.getPosition() != null) {
            existing.setPosition(dto.getPosition());
        }
        if (dto.getHireDate() != null) {
            existing.setHireDate(dto.getHireDate());
        }
        if (dto.getPhoneNumber() != null) {
            existing.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getEmail() != null) {
            existing.setEmail(dto.getEmail());
        }

        Employees updated = employeesRepository.save(existing);

        ApiResponse<EmployeesResponseDTO> response = new ApiResponse<>();
        response.setMessage("Updated successfully");
        response.setSuccess(true);
        response.setData(toDto(updated));

        return response;
    }
}
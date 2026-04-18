
package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.dto.requestDto.EmployeesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.EmployeesResponseDTO;
import com.sprint.pet_shop.entity.Employees;

public interface EmployeesInterface {

    ApiResponse<List<EmployeesResponseDTO>> saveAll(List<EmployeesRequestDTO> employees);

    ApiResponse<List<EmployeesResponseDTO>> getAll();

    ApiResponse<EmployeesResponseDTO> getEmployeesById(long employeesId);

    ApiResponse<String> deleteEmployee(long employeeId);

    ApiResponse<EmployeesResponseDTO> updateEmployee(Long id, EmployeesRequestDTO employee);
    
    ApiResponse<List<EmployeesResponseDTO>> getEmployeesByPosition(String position);
    

}

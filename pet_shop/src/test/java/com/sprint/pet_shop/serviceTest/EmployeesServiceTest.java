package com.sprint.pet_shop.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sprint.pet_shop.dto.requestDto.EmployeesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.EmployeesResponseDTO;
import com.sprint.pet_shop.entity.Employees;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.repository.EmployeesRepository;
import com.sprint.pet_shop.service.EmployeesService;

@ExtendWith(MockitoExtension.class)
public class EmployeesServiceTest {



	    @Mock
	    private EmployeesRepository employeesRepository;

	    @Mock
	    private AddressesRepository addressesRepository;

	    @InjectMocks
	    private EmployeesService employeesService;
		// Test to check saving employees successfully.	
	    @Test
	    void testSaveAll_Positive() {
	        EmployeesRequestDTO dto = new EmployeesRequestDTO();
	        dto.setFirstName("John");
	        dto.setLastName("Doe");
	        dto.setPosition("Staff");
	        dto.setHireDate(java.time.LocalDate.now());
	        dto.setPhoneNumber("9876543210");
	        dto.setEmail("john@gmail.com");
	        dto.setAddressId(1L);

	        Employees saved = new Employees();
	        saved.setEmployeeId(1L);
	        saved.setFirstName("John");

	        when(addressesRepository.findById(1L)).thenReturn(Optional.of(new com.sprint.pet_shop.entity.Addresses()));
	        when(employeesRepository.existsByEmail("john@gmail.com")).thenReturn(false);
	        when(employeesRepository.saveAll(anyList())).thenReturn(List.of(saved));

	        ApiResponse<List<EmployeesResponseDTO>> response =
	                employeesService.saveAll(List.of(dto));

	        assertTrue(response.isSuccess());
	        assertEquals(1, response.getData().size());

	        verify(employeesRepository).saveAll(anyList());
	    }
		// Test to check saving employees when the list is empty.
	    @Test
	    void testSaveAll_EmptyList() {
	        assertThrows(InvalidDataException.class,
	                () -> employeesService.saveAll(List.of()));
	    }
		// Test to check saving employees when email is duplicate.
	    @Test
	    void testSaveAll_DuplicateEmail() {
	        EmployeesRequestDTO dto = new EmployeesRequestDTO();
	        dto.setFirstName("John");
	        dto.setLastName("Doe");
	        dto.setPosition("Staff");
	        dto.setHireDate(java.time.LocalDate.now());
	        dto.setPhoneNumber("9876543210");
	        dto.setEmail("john@gmail.com");
	        dto.setAddressId(1L);

	        when(employeesRepository.existsByEmail("john@gmail.com")).thenReturn(true);

	        assertThrows(DuplicateResourceException.class,
	                () -> employeesService.saveAll(List.of(dto)));
	    }
		// Test to check getting all employees successfully.
	    @Test
	    void testGetAll() {
	        Employees emp = new Employees();
	        emp.setEmployeeId(1L);

	        when(employeesRepository.findAllByOrderByEmployeeIdAsc()).thenReturn(List.of(emp));

	        ApiResponse<List<EmployeesResponseDTO>> response =
	                employeesService.getAll();

	        assertTrue(response.isSuccess());
	        assertEquals(1, response.getData().size());

	        verify(employeesRepository).findAllByOrderByEmployeeIdAsc();
	    }
		// Test to check getting employee by ID when employee is not found.
	    @Test
	    void testGetEmployeeById_NotFound() {
	        when(employeesRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(ResourceNotFoundException.class,
	                () -> employeesService.getEmployeesById(1L));
	    }
	}

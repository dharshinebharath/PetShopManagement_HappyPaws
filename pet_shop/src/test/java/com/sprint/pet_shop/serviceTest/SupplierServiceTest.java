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

import com.sprint.pet_shop.dto.requestDto.SupplierRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.SupplierResponseDTO;
import com.sprint.pet_shop.entity.Supplier;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.repository.SupplierRepository;
import com.sprint.pet_shop.service.SupplierService;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {
	
	

	    @Mock
	    private SupplierRepository supplierRepository;

	    @Mock
	    private AddressesRepository addressesRepository;

	    @InjectMocks
	    private SupplierService supplierService;

		// Test to check saving supplier successfully.
	    @Test
	    void testSaveAll_Positive() {
	        SupplierRequestDTO dto = new SupplierRequestDTO();
	        dto.setName("ABC Suppliers");
	        dto.setContactPerson("John Doe");
	        dto.setPhoneNumber("9876543210");
	        dto.setEmail("abc@gmail.com");
	        dto.setAddressId(1L);

	        Supplier saved = new Supplier();
	        saved.setSupplierId(1L);
	        saved.setName("ABC");

	        when(addressesRepository.findById(1L)).thenReturn(Optional.of(new com.sprint.pet_shop.entity.Addresses()));
	        when(supplierRepository.existsByEmail("abc@gmail.com")).thenReturn(false);
	        when(supplierRepository.saveAll(anyList())).thenReturn(List.of(saved));

	        ApiResponse<List<SupplierResponseDTO>> response =
	                supplierService.saveAll(List.of(dto));

	        assertTrue(response.isSuccess());
	        assertEquals(1, response.getData().size());

	        verify(supplierRepository).saveAll(anyList());
	    }
	    // Test to check saving supplier when list is empty.
	    @Test
	    void testSaveAll_EmptyList() {
	        assertThrows(InvalidDataException.class,
	                () -> supplierService.saveAll(List.of()));
	    }
	    // Test to check saving supplier when supplier is duplicate.
	    @Test
	    void testSaveAll_DuplicateEmail() {
	        SupplierRequestDTO dto = new SupplierRequestDTO();
	        dto.setName("ABC Suppliers");
	        dto.setContactPerson("John Doe");
	        dto.setPhoneNumber("9876543210");
	        dto.setEmail("abc@gmail.com");
	        dto.setAddressId(1L);

	        when(supplierRepository.existsByEmail("abc@gmail.com")).thenReturn(true);

	        assertThrows(DuplicateResourceException.class,
	                () -> supplierService.saveAll(List.of(dto)));
	    }
	    // Test to check getting all suppliers successfully.
	    @Test
	    void testGetAll() {
	        Supplier s = new Supplier();
	        s.setSupplierId(1L);

	        when(supplierRepository.findAllSorted()).thenReturn(List.of(s));

	        ApiResponse<List<SupplierResponseDTO>> response =
	                supplierService.getAll();

	        assertTrue(response.isSuccess());
	        assertEquals(1, response.getData().size());

	        verify(supplierRepository).findAllSorted();
	    }
		// Test to check getting supplier by ID successfully.
	    @Test
	    void testGetSupplierById_NotFound() {
	        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(ResourceNotFoundException.class,
	                () -> supplierService.getSupplierById(1L));
	    }
	}

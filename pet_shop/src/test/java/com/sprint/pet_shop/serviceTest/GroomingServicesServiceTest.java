package com.sprint.pet_shop.serviceTest;


import com.sprint.pet_shop.dto.requestDto.GroomingServicesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.entity.GroomingServices;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.GroomingServicesRepository;
import com.sprint.pet_shop.service.GroomingServicesService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Unit tests for GroomingServicesService
// Mocks the repository and tests the business logic
@ExtendWith(MockitoExtension.class)
class GroomingServicesServiceTest {

    // Mock repository to avoid real database calls
    @Mock
    private GroomingServicesRepository repository;

    // Inject the service
    @InjectMocks
    private GroomingServicesService service;
    
    // Test case for successful saving of grooming services
    @Test
    void saveAllGroomingServices_success() {

        // Create a request DTO
        GroomingServicesRequestDTO dto = new GroomingServicesRequestDTO();
        dto.setName("Bath");
        dto.setDescription("Full bath service");
        dto.setPrice(BigDecimal.valueOf(500));
        dto.setAvailable(true);

        // Create an entity
        GroomingServices entity = new GroomingServices();
        entity.setServiceId(1L);
        entity.setName("Bath");
        entity.setDescription("Full bath service");
        entity.setPrice(BigDecimal.valueOf(500));
        entity.setAvailable(true);

        // Mock repository methods
        when(repository.existsByName("Bath")).thenReturn(false);
        when(repository.saveAll(anyList())).thenReturn(List.of(entity));

        ApiResponse<?> response = service.saveAllGroomingServices(List.of(dto));

        // Assertions for success
        assertTrue(response.isSuccess());
        assertEquals("Grooming services saved successfully", response.getMessage());

        verify(repository, times(1)).saveAll(anyList());
    }

    // Test case for duplicate grooming services
    @Test
    void saveAllGroomingServices_duplicateService_throwsException() {

        // Create a request DTO
        GroomingServicesRequestDTO dto = new GroomingServicesRequestDTO();
        dto.setName("Bath");
        dto.setPrice(BigDecimal.valueOf(500));
        dto.setAvailable(true);

        // Mock repository methods
        when(repository.existsByName("Bath")).thenReturn(true);

        // Assertions for duplicate service
        assertThrows(DuplicateResourceException.class,
                () -> service.saveAllGroomingServices(List.of(dto)));

        verify(repository, never()).saveAll(anyList());
    }

    // Test case for invalid price
    @Test
    void saveAllGroomingServices_invalidPrice_throwsException() {

        // Create a request DTO
        GroomingServicesRequestDTO dto = new GroomingServicesRequestDTO();
        dto.setName("Bath");
        dto.setPrice(BigDecimal.valueOf(-100));
        dto.setAvailable(true);

        // Assertions for invalid price
        assertThrows(InvalidDataException.class,
                () -> service.saveAllGroomingServices(List.of(dto)));

        verify(repository, never()).saveAll(anyList());
    }
    
    // Test case for getting grooming services by ID
    @Test
    void getGroomingServiceById_notFound() {

        // Mock repository methods
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Assertions for not found
        assertThrows(ResourceNotFoundException.class,
                () -> service.getGroomingServiceById(1L));

        verify(repository, times(1)).findById(1L);
    }

    // Test case for deleting grooming services
    @Test
    void deleteGroomingService_success() {

        // Create an entity
        GroomingServices entity = new GroomingServices();
        entity.setServiceId(1L);

        // Mock repository methods
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        ApiResponse<String> response = service.deleteGroomingServiceById(1L);

        // Assertions for success
        assertTrue(response.isSuccess());
        assertEquals("Deleted successfully", response.getMessage());

        verify(repository, times(1)).delete(entity);
    }
    
}

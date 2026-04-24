// This service contains the main business flow for grooming services service test.
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

@ExtendWith(MockitoExtension.class)
class GroomingServicesServiceTest {

    @Mock
    private GroomingServicesRepository repository;

    @InjectMocks
    private GroomingServicesService service;
    
    @Test
    void saveAllGroomingServices_success() {

        GroomingServicesRequestDTO dto = new GroomingServicesRequestDTO();
        dto.setName("Bath");
        dto.setDescription("Full bath service");
        dto.setPrice(BigDecimal.valueOf(500));
        dto.setAvailable(true);

        GroomingServices entity = new GroomingServices();
        entity.setServiceId(1L);
        entity.setName("Bath");
        entity.setDescription("Full bath service");
        entity.setPrice(BigDecimal.valueOf(500));
        entity.setAvailable(true);

        when(repository.existsByName("Bath")).thenReturn(false);
        when(repository.saveAll(anyList())).thenReturn(List.of(entity));

        ApiResponse<?> response = service.saveAllGroomingServices(List.of(dto));

        assertTrue(response.isSuccess());
        assertEquals("Grooming services saved successfully", response.getMessage());

        verify(repository, times(1)).saveAll(anyList());
    }

    @Test
    void saveAllGroomingServices_duplicateService_throwsException() {

        GroomingServicesRequestDTO dto = new GroomingServicesRequestDTO();
        dto.setName("Bath");
        dto.setPrice(BigDecimal.valueOf(500));
        dto.setAvailable(true);

        when(repository.existsByName("Bath")).thenReturn(true);

        assertThrows(DuplicateResourceException.class,
                () -> service.saveAllGroomingServices(List.of(dto)));

        verify(repository, never()).saveAll(anyList());
    }

    @Test
    void saveAllGroomingServices_invalidPrice_throwsException() {

        GroomingServicesRequestDTO dto = new GroomingServicesRequestDTO();
        dto.setName("Bath");
        dto.setPrice(BigDecimal.valueOf(-100));
        dto.setAvailable(true);

        assertThrows(InvalidDataException.class,
                () -> service.saveAllGroomingServices(List.of(dto)));

        verify(repository, never()).saveAll(anyList());
    }
    
    @Test
    void getGroomingServiceById_notFound() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.getGroomingServiceById(1L));

        verify(repository, times(1)).findById(1L);
    }

    
    @Test
    void deleteGroomingService_success() {

        GroomingServices entity = new GroomingServices();
        entity.setServiceId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        ApiResponse<String> response = service.deleteGroomingServiceById(1L);

        assertTrue(response.isSuccess());
        assertEquals("Deleted successfully", response.getMessage());

        verify(repository, times(1)).delete(entity);
    }
    
}

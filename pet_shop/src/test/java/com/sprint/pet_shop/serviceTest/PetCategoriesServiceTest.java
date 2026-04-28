package com.sprint.pet_shop.serviceTest;

import com.sprint.pet_shop.dto.requestDto.PetCategoriesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetCategoriesResponseDTO;
import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.PetCategoriesRepository;
import com.sprint.pet_shop.service.PetCategoriesService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetCategoriesServiceTest {

    @Mock
    private PetCategoriesRepository repository;

    @InjectMocks
    private PetCategoriesService service;

    private PetCategories category;
    private PetCategoriesRequestDTO dto;

    // Test setup.
    @BeforeEach
    void setUp() {
        category = new PetCategories();
        category.setCategory_id(1L);
        category.setName("Dog");

        dto = new PetCategoriesRequestDTO();
        dto.setName("Dog");
    }
    // Test to check saving pet categories successfully.
    @Test
    void testAddAll_Positive() {

        when(repository.saveAll(anyList())).thenReturn(List.of(category));

        ApiResponse<List<PetCategoriesResponseDTO>> response =
                service.addAll(List.of(dto));

        assertTrue(response.isSuccess());
        assertEquals(1, response.getData().size());
    }
    // Test to check saving pet categories when name is invalid.
    @Test
    void testAddAll_InvalidName() {

        dto.setName("");

        assertThrows(InvalidDataException.class,
                () -> service.addAll(List.of(dto)));
    }
    // Test to check getting pet category by ID successfully.
    @Test
    void testGetById_Positive() {

        when(repository.findById(1L)).thenReturn(Optional.of(category));

        ApiResponse<PetCategoriesResponseDTO> response =
                service.getCategoryById(1L);

        assertEquals("Dog", response.getData().getName());
    }
    // Test to check getting pet category by ID when pet category is not found.
    @Test
    void testGetById_NotFound() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.getCategoryById(1L));
    }
    // Test to check deleting pet category when pet category is not found.
    @Test
    void testDelete_NotFound() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.deleteCategory(1L));
    }
}

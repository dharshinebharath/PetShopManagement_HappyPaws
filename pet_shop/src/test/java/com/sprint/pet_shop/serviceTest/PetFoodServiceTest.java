package com.sprint.pet_shop.serviceTest;


import com.sprint.pet_shop.dto.requestDto.PetFoodRequestDTO;
import com.sprint.pet_shop.entity.PetFood;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.PetFoodRepository;
import com.sprint.pet_shop.service.PetFoodService;

import org.junit.jupiter.api.BeforeEach;
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
class PetFoodServiceTest {

    @Mock
    private PetFoodRepository petFoodRepository;

    @InjectMocks
    private PetFoodService petFoodService;

    private PetFoodRequestDTO requestDTO;
    private PetFood petFood;

    @BeforeEach
    void setUp() {

        requestDTO = new PetFoodRequestDTO();
        requestDTO.setName("Dog Food");
        requestDTO.setBrand("Pedigree");
        requestDTO.setType("Dry");
        requestDTO.setQuantity(10);
        requestDTO.setPrice(BigDecimal.valueOf(500));

        petFood = new PetFood();
        petFood.setFoodId(1L);
        petFood.setName("Dog Food");
        petFood.setBrand("Pedigree");
        petFood.setType("Dry");
        petFood.setQuantity(10);
        petFood.setPrice(BigDecimal.valueOf(500));
    }
    @Test
    void saveAllPetFood_success() {

        when(petFoodRepository.existsByNameAndBrand("Dog Food", "Pedigree"))
                .thenReturn(false);

        when(petFoodRepository.saveAll(anyList()))
                .thenReturn(List.of(petFood));

        var response = petFoodService.saveAllPetFood(List.of(requestDTO));

        assertTrue(response.isSuccess());
        assertEquals("Pet food saved successfully", response.getMessage());
        assertEquals(1, response.getData().size());

        verify(petFoodRepository, times(1)).saveAll(anyList());
    }
    @Test
    void saveAllPetFood_invalidQuantity_throwsException() {

        requestDTO.setQuantity(-5);

        assertThrows(InvalidDataException.class,
                () -> petFoodService.saveAllPetFood(List.of(requestDTO)));

        verify(petFoodRepository, never()).saveAll(anyList());
    }
    @Test
    void saveAllPetFood_duplicateFood_throwsException() {

        when(petFoodRepository.existsByNameAndBrand("Dog Food", "Pedigree"))
                .thenReturn(true);

        assertThrows(DuplicateResourceException.class,
                () -> petFoodService.saveAllPetFood(List.of(requestDTO)));

        verify(petFoodRepository, never()).saveAll(anyList());
    }
    @Test
    void getPetFoodById_success() {

        when(petFoodRepository.findById(1L))
                .thenReturn(Optional.of(petFood));

        var response = petFoodService.getPetFoodById(1L);

        assertTrue(response.isSuccess());
        assertEquals("Pet food fetched successfully", response.getMessage());
        assertEquals("Dog Food", response.getData().getName());

        verify(petFoodRepository).findById(1L);
    }
    @Test
    void getPetFoodById_notFound_throwsException() {

        when(petFoodRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> petFoodService.getPetFoodById(99L));

        verify(petFoodRepository).findById(99L);
    }
}

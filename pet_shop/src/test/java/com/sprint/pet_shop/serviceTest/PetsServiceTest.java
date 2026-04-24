package com.sprint.pet_shop.serviceTest;

import com.sprint.pet_shop.dto.requestDto.PetsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetsResponseDTO;
import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.*;

import com.sprint.pet_shop.service.PetsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetsServiceTest {

    @Mock private PetsRepository petsRepository;
    @Mock private PetCategoriesRepository categoryRepository;
    @Mock private GroomingServicesRepository groomingRepo;
    @Mock private PetFoodRepository foodRepo;
    @Mock private VaccinationsRepository vaccinationRepo;
    @Mock private EmployeesRepository employeeRepo;
    @Mock private SupplierRepository supplierRepo;

    @InjectMocks
    private PetsService petsService;

    private Pets pet;
    private PetsRequestDTO dto;
    private PetCategories category;

    @BeforeEach
    void setUp() {
        category = new PetCategories();
        category.setCategory_id(1L);
        category.setName("Dog");

        pet = new Pets();
        pet.setPet_id(1L);
        pet.setName("Tommy");
        pet.setBreed("Labrador");
        pet.setAge(2);
        pet.setPrice(new BigDecimal("10000"));
        pet.setCategory(category);

        dto = new PetsRequestDTO();
        dto.setName("Tommy");
        dto.setBreed("Labrador");
        dto.setAge(2);
        dto.setPrice(new BigDecimal("10000"));
        dto.setCategory_id(1L);
    }
    @Test
    void testAddAllPets_Positive() {

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(petsRepository.saveAll(anyList())).thenReturn(List.of(pet));

        ApiResponse<List<PetsResponseDTO>> response =
                petsService.addAllPets(List.of(dto));

        assertTrue(response.isSuccess());
        assertEquals(1, response.getData().size());
    }
    @Test
    void testAddAllPets_InvalidName() {

        dto.setName("");

        assertThrows(InvalidDataException.class,
                () -> petsService.addAllPets(List.of(dto)));
    }
    @Test
    void testAddAllPets_CategoryNotFound() {

        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> petsService.addAllPets(List.of(dto)));
    }
    @Test
    void testGetPetById_Positive() {

        when(petsRepository.findById(1L)).thenReturn(Optional.of(pet));

        ApiResponse<PetsResponseDTO> response =
                petsService.getPetById(1L);

        assertEquals("Tommy", response.getData().getName());
    }
    @Test
    void testDeletePet_NotFound() {

        when(petsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> petsService.deletePets(1L));
    }
}

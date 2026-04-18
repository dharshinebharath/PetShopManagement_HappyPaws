package com.sprint.pet_shop.serviceTest;


import com.sprint.pet_shop.dto.requestDto.VaccinationsRequestDTO;
import com.sprint.pet_shop.entity.Vaccinations;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.VaccinationsRepository;
import com.sprint.pet_shop.service.VaccinationsService;

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
class VaccinationsServiceTest {

    @Mock
    private VaccinationsRepository vaccinationsRepository;

    @InjectMocks
    private VaccinationsService vaccinationsService;

    private VaccinationsRequestDTO requestDTO;
    private Vaccinations vaccination;

    @BeforeEach
    void setUp() {

        requestDTO = new VaccinationsRequestDTO();
        requestDTO.setName("Rabies Vaccine");
        requestDTO.setDescription("Protects against rabies");
        requestDTO.setPrice(BigDecimal.valueOf(500));
        requestDTO.setAvailable(true);

        vaccination = new Vaccinations();
        vaccination.setVaccinationId(1L);
        vaccination.setName("Rabies Vaccine");
        vaccination.setDescription("Protects against rabies");
        vaccination.setPrice(BigDecimal.valueOf(500));
        vaccination.setAvailable(true);
    }

    // ✅ 1. Positive: Save Vaccinations
    @Test
    void saveAllVaccinations_success() {

        when(vaccinationsRepository.existsByName("Rabies Vaccine"))
                .thenReturn(false);

        when(vaccinationsRepository.saveAll(anyList()))
                .thenReturn(List.of(vaccination));

        var response = vaccinationsService.saveAllVaccinations(List.of(requestDTO));

        assertTrue(response.isSuccess());
        assertEquals("Vaccinations saved successfully", response.getMessage());
        assertEquals(1, response.getData().size());

        verify(vaccinationsRepository, times(1)).saveAll(anyList());
    }

    // ❌ 2. Negative: Invalid price
    @Test
    void saveAllVaccinations_invalidPrice_throwsException() {

        requestDTO.setPrice(BigDecimal.valueOf(-100));

        assertThrows(InvalidDataException.class,
                () -> vaccinationsService.saveAllVaccinations(List.of(requestDTO)));

        verify(vaccinationsRepository, never()).saveAll(anyList());
    }

    // ❌ 3. Negative: Duplicate vaccination
    @Test
    void saveAllVaccinations_duplicate_throwsException() {

        when(vaccinationsRepository.existsByName("Rabies Vaccine"))
                .thenReturn(true);

        assertThrows(DuplicateResourceException.class,
                () -> vaccinationsService.saveAllVaccinations(List.of(requestDTO)));

        verify(vaccinationsRepository, never()).saveAll(anyList());
    }

    // ✅ 4. Positive: Get by ID
    @Test
    void getVaccinationById_success() {

        when(vaccinationsRepository.findById(1L))
                .thenReturn(Optional.of(vaccination));

        var response = vaccinationsService.getVaccinationsById(1L);

        assertTrue(response.isSuccess());
        assertEquals("Vaccination fetched successfully", response.getMessage());
        assertEquals("Rabies Vaccine", response.getData().getName());

        verify(vaccinationsRepository).findById(1L);
    }

    // ❌ 5. Negative: Not found
    @Test
    void getVaccinationById_notFound_throwsException() {

        when(vaccinationsRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> vaccinationsService.getVaccinationsById(99L));

        verify(vaccinationsRepository).findById(99L);
    }
}
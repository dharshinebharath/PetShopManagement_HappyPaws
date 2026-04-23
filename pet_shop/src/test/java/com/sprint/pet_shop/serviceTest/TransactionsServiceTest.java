package com.sprint.pet_shop.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sprint.pet_shop.dto.requestDto.TransactionsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.TransactionsResponseDTO;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.TransactionStatus;
import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.CustomersRepository;
import com.sprint.pet_shop.repository.PetsRepository;
import com.sprint.pet_shop.repository.TransactionsRepository;
import com.sprint.pet_shop.service.TransactionsService;

@ExtendWith(MockitoExtension.class)
public class TransactionsServiceTest {

    @Mock
    private TransactionsRepository transactionsRepository;

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private PetsRepository petsRepository;

    @InjectMocks
    private TransactionsService transactionsService;

    private Customers customer;
    private Pets pet;

    @BeforeEach
    void setup() {
        customer = new Customers();
        customer.setCustomerId(1L);

        pet = new Pets();
        pet.setPet_id(1L);
    }

    // =========================
    // ✅ POSITIVE TEST CASES
    // =========================

    @Test
    void testSaveTransaction_Positive() {

        TransactionsRequestDTO dto = new TransactionsRequestDTO();
        dto.setCustomerId(1L);
        dto.setPetId(1L);
        dto.setAmount(new BigDecimal("500"));
        dto.setTransactionDate(LocalDate.now());
        dto.setTransactionStatus(TransactionStatus.SUCCESS); // ✅ correct enum

        TransactionsEntity saved = new TransactionsEntity();
        saved.setTransactionId(1L);
        saved.setCustomer(customer);
        saved.setPet(pet);
        saved.setAmount(dto.getAmount());
        saved.setTransactionStatus(TransactionStatus.SUCCESS);

        when(customersRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(petsRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(transactionsRepository.save(any(TransactionsEntity.class))).thenReturn(saved);

        ApiResponse<TransactionsResponseDTO> response = transactionsService.save(dto);

        assertTrue(response.isSuccess());
        assertEquals("Transaction saved successfully", response.getMessage());

        verify(transactionsRepository).save(any(TransactionsEntity.class));
    }

    @Test
    void testGetAll_Positive() {

        TransactionsEntity entity = new TransactionsEntity();
        entity.setTransactionId(1L);
        entity.setCustomer(customer);
        entity.setPet(pet);
        entity.setAmount(new BigDecimal("200"));
        entity.setTransactionStatus(TransactionStatus.SUCCESS);

        when(transactionsRepository.findAll()).thenReturn(List.of(entity));

        ApiResponse<List<TransactionsResponseDTO>> response = transactionsService.getAll();

        assertTrue(response.isSuccess());
        assertEquals(1, response.getData().size());

        verify(transactionsRepository).findAll();
    }

    @Test
    void testGetById_Positive() {

        TransactionsEntity entity = new TransactionsEntity();
        entity.setTransactionId(1L);
        entity.setCustomer(customer);
        entity.setPet(pet);
        entity.setTransactionStatus(TransactionStatus.SUCCESS);

        when(transactionsRepository.findById(1L)).thenReturn(Optional.of(entity));

        ApiResponse<TransactionsResponseDTO> response = transactionsService.getById(1L);

        assertTrue(response.isSuccess());

        verify(transactionsRepository).findById(1L);
    }

    @Test
    void testUpdate_Positive() {

        TransactionsRequestDTO dto = new TransactionsRequestDTO();
        dto.setCustomerId(1L);
        dto.setPetId(1L);
        dto.setAmount(new BigDecimal("300"));
        dto.setTransactionDate(LocalDate.now());
        dto.setTransactionStatus(TransactionStatus.SUCCESS);

        TransactionsEntity existing = new TransactionsEntity();
        existing.setTransactionId(1L);
        existing.setCustomer(customer);
        existing.setPet(pet);

        when(transactionsRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(transactionsRepository.save(any())).thenReturn(existing);

        ApiResponse<TransactionsResponseDTO> response = transactionsService.update(1L, dto);

        assertTrue(response.isSuccess());

        verify(transactionsRepository).save(any());
    }

    @Test
    void testDelete_Positive() {

        when(transactionsRepository.existsById(1L)).thenReturn(true);

        ApiResponse<String> response = transactionsService.delete(1L);

        assertTrue(response.isSuccess());

        verify(transactionsRepository).deleteById(1L);
    }

    // =========================
    // ❌ NEGATIVE TEST CASES
    // =========================

    @Test
    void testSave_NullIds_Negative() {

        TransactionsRequestDTO dto = new TransactionsRequestDTO();
        dto.setCustomerId(null);
        dto.setPetId(null);

        assertThrows(InvalidDataException.class,
                () -> transactionsService.save(dto));
    }

    @Test
    void testSave_CustomerNotFound_Negative() {

        TransactionsRequestDTO dto = new TransactionsRequestDTO();
        dto.setCustomerId(1L);
        dto.setPetId(1L);

        when(customersRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> transactionsService.save(dto));
    }

    @Test
    void testSave_PetNotFound_Negative() {

        TransactionsRequestDTO dto = new TransactionsRequestDTO();
        dto.setCustomerId(1L);
        dto.setPetId(1L);

        when(customersRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(petsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> transactionsService.save(dto));
    }

    @Test
    void testGetById_NotFound_Negative() {

        when(transactionsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> transactionsService.getById(1L));
    }

    @Test
    void testDelete_NotFound_Negative() {

        when(transactionsRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> transactionsService.delete(1L));
    }
}
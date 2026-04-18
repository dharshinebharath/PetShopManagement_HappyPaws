package com.sprint.pet_shop.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Date;
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
import com.sprint.pet_shop.entity.*;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.*;
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
    private TransactionsService service;

    private TransactionsRequestDTO dto;
    private Customers customer;
    private Pets pet;
    private TransactionsEntity entity;

    @BeforeEach
    void setup() {
        dto = new TransactionsRequestDTO();
        dto.setCustomerId(1L);
        dto.setPetId(2L);
        dto.setAmount(BigDecimal.valueOf(500));
        dto.setTransactionDate(Date.valueOf("2024-01-01"));
        dto.setTransactionStatus("Success");

        customer = new Customers();
        customer.setCustomerId(1L);

        pet = new Pets();
        pet.setPet_id(2L);

        entity = new TransactionsEntity();
        entity.setTransactionId(10L);
        entity.setCustomer(customer);
        entity.setPet(pet);
        entity.setAmount(dto.getAmount());
        entity.setTransactionDate(dto.getTransactionDate());
        entity.setTransactionStatus(TransactionStatus.Success);
    }


    // POSITIVE TEST CASES
    

    @Test
    void testSave_Success() {
        when(customersRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(petsRepository.findById(2L)).thenReturn(Optional.of(pet));
        when(transactionsRepository.save(any())).thenReturn(entity);

        ApiResponse<TransactionsResponseDTO> response = service.save(dto);

        assertTrue(response.isSuccess());
        assertEquals("Transaction saved successfully", response.getMessage());

        verify(transactionsRepository).save(any());
    }

    @Test
    void testGetAll_Success() {
        when(transactionsRepository.findAll()).thenReturn(List.of(entity));

        ApiResponse<List<TransactionsResponseDTO>> response = service.getAll();

        assertTrue(response.isSuccess());
        assertEquals(1, response.getData().size());

        verify(transactionsRepository).findAll();
    }

    @Test
    void testGetById_Success() {
        when(transactionsRepository.findById(10L)).thenReturn(Optional.of(entity));

        ApiResponse<TransactionsResponseDTO> response = service.getById(10L);

        assertTrue(response.isSuccess());
        assertEquals(10L, response.getData().getTransactionId());

        verify(transactionsRepository).findById(10L);
    }

    @Test
    void testUpdate_Success() {
        when(transactionsRepository.findById(10L)).thenReturn(Optional.of(entity));
        when(transactionsRepository.save(any())).thenReturn(entity);

        ApiResponse<TransactionsResponseDTO> response = service.update(10L, dto);

        assertTrue(response.isSuccess());
        assertEquals("Transaction updated successfully", response.getMessage());

        verify(transactionsRepository).save(any());
    }

    @Test
    void testDelete_Success() {
        when(transactionsRepository.existsById(10L)).thenReturn(true);

        ApiResponse<String> response = service.delete(10L);

        assertTrue(response.isSuccess());

        verify(transactionsRepository).deleteById(10L);
    }

    
    // NEGATIVE TEST CASES
    

    @Test
    void testSave_InvalidData() {
        dto.setCustomerId(null);

        assertThrows(InvalidDataException.class, () -> service.save(dto));
    }

    @Test
    void testSave_CustomerNotFound() {
        when(customersRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.save(dto));
    }

    @Test
    void testGetById_NotFound() {
        when(transactionsRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getById(99L));
    }

    @Test
    void testUpdate_NotFound() {
        when(transactionsRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.update(99L, dto));
    }

    @Test
    void testDelete_NotFound() {
        when(transactionsRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.delete(99L));
    }
}
